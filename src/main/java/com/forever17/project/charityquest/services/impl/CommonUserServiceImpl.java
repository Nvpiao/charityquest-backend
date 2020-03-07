package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.StatusType;
import com.forever17.project.charityquest.enums.UserType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.mapper.CharityUserMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.CharityUserExample;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import com.forever17.project.charityquest.pojos.entity.ResetPasswordCode;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.CommonUserService;
import com.forever17.project.charityquest.utils.MD5Util;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Implementation of CommonUserServiceImpl
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 7 Mar 2020
 * @since 1.0
 */
@Slf4j
@Service
public class CommonUserServiceImpl implements CommonUserService {

    /**
     * code for resetting password
     */
    private final ResetPasswordCode resetPasswordCode;

    /**
     * java mail sender
     */
    private final JavaMailSender javaMailSender;

    /**
     * public user mapper
     */
    private final PublicUserMapper publicUserMapper;

    /**
     * charity user mapper
     */
    private final CharityUserMapper charityUserMapper;

    /**
     * Example of PublicUser class
     */
    PublicUserExample publicUserExample;

    /**
     * Example of CharityUser class
     */
    CharityUserExample charityUserExample;

    {
        // static initialization
        publicUserExample = new PublicUserExample();
        charityUserExample = new CharityUserExample();
    }

    @Autowired
    public CommonUserServiceImpl(ResetPasswordCode resetPasswordCode, JavaMailSender javaMailSender,
                                 PublicUserMapper publicUserMapper, CharityUserMapper charityUserMapper) {
        this.resetPasswordCode = resetPasswordCode;
        this.javaMailSender = javaMailSender;
        this.publicUserMapper = publicUserMapper;
        this.charityUserMapper = charityUserMapper;
    }

    @Override
    public ReturnStatus sendResetPassword(String email) throws SystemInternalException {
        UserType userType = null;

        // query charity user
        charityUserExample.clear();
        charityUserExample.createCriteria().andEmailEqualTo(email);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);
        if (!charityUsers.isEmpty()) {
            userType = UserType.CHARITY;
        }

        // query public user
        publicUserExample.clear();
        publicUserExample.createCriteria().andEmailEqualTo(email);
        List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);
        if (!publicUsers.isEmpty()) {
            userType = UserType.PUBLIC;
        }

        // check if user exist
        if (publicUsers.isEmpty() && charityUsers.isEmpty()) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_EMAIL, email));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }

        // simple mail message
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(CharityConstants.MAIL_SUBJECT_RESET_PASSWORD);

        // generate code
        String code = resetPasswordCode.generateCode(email);
        String text;
        try {
            assert userType != null;
            text = CharityConstants.MAIL_TEXT_RESET_PASSWORD + '\n' +
                    resetPasswordCode.getBaseUrl() + CharityConstants.MAIL_TEXT_RESET_PASSWORD_CODE +
                    URLEncoder.encode(code, StandardCharsets.UTF_8.toString()) +
                    CharityConstants.MAIL_TEXT_RESET_PASSWORD_TYPE + userType.getCode() +
                    CharityConstants.MAIL_TEXT_RESET_PASSWORD_EMAIL + email;
        } catch (UnsupportedEncodingException e) {
            log.error(CharityConstants.UNSUPPORTED_ENCODING_ERROR, e);
            throw new SystemInternalException(CharityConstants.UNSUPPORTED_ENCODING_ERROR);
        }

        // set text
        msg.setText(text);

        // send email
        javaMailSender.send(msg);

        // return success
        return new ReturnStatus(CharityConstants.RETURN_SEND_RESET_EMAIL_SUCCESS);
    }

    @Override
    @Transactional
    public ReturnStatus resetPassword(String email, String password, String code, UserType userType)
            throws SystemInternalException {
        if (userType.equals(UserType.PUBLIC)) {
            // query public user by email
            publicUserExample.clear();
            publicUserExample.createCriteria().andEmailEqualTo(email);
            List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

            return resetPublicUserPassword(email, publicUsers, password, code);

        } else {
            // query charity user by email
            charityUserExample.clear();
            charityUserExample.createCriteria().andEmailEqualTo(email);
            List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

            return resetCharityUserPassword(email, charityUsers, password, code);
        }
    }

    /**
     * reset password for public user.
     *
     * @param email       email address
     * @param publicUsers user of public
     * @param password    password of public
     * @param code        code for resetting password
     * @return instance of ReturnStatus
     */
    @SuppressWarnings("unchecked")
    private ReturnStatus resetPublicUserPassword(String email, List<PublicUser> publicUsers, String password, String code) throws SystemInternalException {
        if (publicUsers.isEmpty()) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_EMAIL, email));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }

        PublicUser user = publicUsers.get(0);
        ReturnStatus codeStatus = checkResetPasswordCode(user.getEmail(), code);
        if (StatusType.SUCCESS.equals(codeStatus.getStatusType())) {
            ReturnStatus passwordStatus = checkPassword(user.getPassword(), password, user.getId());
            if (StatusType.SUCCESS.equals(passwordStatus.getStatusType())) {
                // update password
                String md5Password = ((Map<String, String>) passwordStatus.getData()).get(CharityConstants.MD5_PASSWORD);
                publicUserMapper.updateByPrimaryKeySelective(new PublicUser(user.getId(), md5Password));
                return new ReturnStatus(CharityConstants.RETURN_RESET_PASSWORD_SUCCESS);
            } else {
                return passwordStatus;
            }
        } else {
            return codeStatus;
        }
    }

    /**
     * reset password for charity user.
     *
     * @param email        email address
     * @param charityUsers user of charity
     * @param password     password of charity
     * @param code         code for resetting password
     * @return instance of ReturnStatus
     */
    @SuppressWarnings("unchecked")
    private ReturnStatus resetCharityUserPassword(String email, List<CharityUser> charityUsers, String password, String code)
            throws SystemInternalException {
        if (charityUsers.isEmpty()) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_EMAIL, email));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }

        CharityUser user = charityUsers.get(0);
        ReturnStatus codeStatus = checkResetPasswordCode(user.getEmail(), code);
        if (StatusType.SUCCESS.equals(codeStatus.getStatusType())) {
            ReturnStatus passwordStatus = checkPassword(user.getPassword(), password, user.getId());
            if (StatusType.SUCCESS.equals(passwordStatus.getStatusType())) {
                // update password
                String md5Password = ((Map<String, String>) passwordStatus.getData()).get(CharityConstants.MD5_PASSWORD);
                charityUserMapper.updateByPrimaryKeySelective(new CharityUser(user.getId(), md5Password));
                return new ReturnStatus(CharityConstants.RETURN_RESET_PASSWORD_SUCCESS);
            } else {
                return passwordStatus;
            }
        } else {
            return codeStatus;
        }
    }

    /**
     * check if code can be used or not.
     *
     * @param email email address
     * @param code  code for resetting password
     * @return instance of ReturnStatus
     */
    private ReturnStatus checkResetPasswordCode(String email, String code)
            throws SystemInternalException {
        // decode
        String deCode;
        try {
            deCode = URLDecoder.decode(code, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            log.error(CharityConstants.UNSUPPORTED_ENCODING_ERROR, e);
            throw new SystemInternalException(CharityConstants.UNSUPPORTED_ENCODING_ERROR);
        }
        // check code
        boolean codeStatus = resetPasswordCode.testCode(deCode, email);
        if (Boolean.FALSE == codeStatus) {
            log.error(CharityConstants.RETURN_RESET_PASSWORD_CODE_EXPIRE_OR_USED);
            return new ReturnStatus(CharityConstants.RETURN_RESET_PASSWORD_CODE_EXPIRE_OR_USED,
                    CharityCodes.RESET_PASSWORD_CODE_EXPIRE_OR_USED, StatusType.FAIL);
        }
        return new ReturnStatus(CharityConstants.RETURN_RESET_PASSWORD_CODE_CAN_USE);
    }

    /**
     * check if password is the same with previous one.
     *
     * @param oldPassword old password
     * @param newPassword new password
     * @param id          id of user
     * @return instance of ReturnStatus
     * @throws SystemInternalException system internal exception
     */
    private ReturnStatus checkPassword(String oldPassword, String newPassword, String id) throws SystemInternalException {
        // md5 incoming password
        String md5Password;
        try {
            // convert password into md5 code
            md5Password = MD5Util.md5(newPassword);
        } catch (NoSuchAlgorithmException e) {
            log.error(CharityConstants.NO_SUCH_ALGORITHM_ERROR, e);
            throw new SystemInternalException(CharityConstants.NO_SUCH_ALGORITHM_ERROR);
        }

        if (md5Password.equals(oldPassword)) {
            // password can not be the same as previous.
            log.warn(String.format(CharityConstants.LOG_CHANGE_PASSWORD_DUPLICATE, id));
            return new ReturnStatus(CharityConstants.RETURN_PASSWORD_DUPLICATED_ERROR,
                    CharityCodes.CHANGE_PASSWORD_DUPLICATE, StatusType.WARN);
        }

        // success return
        return new ReturnStatus(CharityConstants.RETURN_CHANGE_PASSWORD_SUCCESS,
                ImmutableMap.of(CharityConstants.MD5_PASSWORD, md5Password));
    }
}
