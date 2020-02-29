package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.StatusType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.mapper.CharityUserMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.CharityUserExample;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.PublicUserService;
import com.forever17.project.charityquest.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of PublicUserService
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Slf4j
@Service
public class PublicUserServiceImpl implements PublicUserService {

    /**
     * uuid generator
     */
    private final UUID uuid;

    /**
     * http session
     */
    private final HttpSession httpSession;

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
    public PublicUserServiceImpl(UUID uuid, HttpSession httpSession, PublicUserMapper publicUserMapper,
                                 CharityUserMapper charityUserMapper) {
        this.uuid = uuid;
        this.httpSession = httpSession;
        this.publicUserMapper = publicUserMapper;
        this.charityUserMapper = charityUserMapper;
    }

    @Override
    public ReturnStatus addUser(PublicUser publicUser) throws SystemInternalException {
        String userId = uuid.toString();
        String md5Password;
        String password = publicUser.getPassword();

        try {
            // convert password into md5 code
            md5Password = MD5Util.md5(password);
        } catch (NoSuchAlgorithmException e) {
            log.error(CharityConstants.NO_SUCH_ALGORITHM_ERROR, e);
            throw new SystemInternalException();
        }
        // set uuid
        publicUser.setId(userId);
        // set md5 password
        publicUser.setPassword(md5Password);

        // email check
        ReturnStatus emailStatus = checkEmail(publicUser.getEmail());
        if (emailStatus.getStatusType().equals(StatusType.WARN)) {
            return emailStatus;
        }

        // insert into database
        publicUserMapper.insert(publicUser);

        // return success result status
        return new ReturnStatus(CharityConstants.RETURN_PUBLIC_USER_ADDED);
    }

    @Override
    public ReturnStatus checkEmail(String email) {
        // query public user by email address
        publicUserExample.clear();
        publicUserExample.createCriteria().andEmailEqualTo(email);
        List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

        // query charity user by email address
        charityUserExample.clear();
        charityUserExample.createCriteria().andEmailEqualTo(email);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        // judge
        if (publicUsers.isEmpty() && charityUsers.isEmpty()) {
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_CAN_BE_USED);
        } else {
            log.warn(CharityConstants.RETURN_EMAIL_CAN_NOT_BE_USED);
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_CAN_NOT_BE_USED,
                    CharityCodes.EMAIL_ADDRESS_CAN_NOT_BE_USED, StatusType.WARN);
        }
    }

    @Override
    public ReturnStatus signIn(String email, String password) throws SystemInternalException {
        // query public user by email address
        publicUserExample.clear();
        publicUserExample.createCriteria().andEmailEqualTo(email);
        List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

        if (!publicUsers.isEmpty()) {
            assert publicUsers.size() == 1;

            // get this user
            PublicUser publicUser = publicUsers.get(0);
            String userPassword = publicUser.getPassword();
            String userId = publicUser.getId();

            // success return
            ReturnStatus successReturn = new ReturnStatus(CharityConstants.RETURN_USER_LOGIN_SUCCESS);

//            // check if user has already logged in.
//            String token = (String) httpSession.getAttribute(CharityConstants.HEADER_REQUEST_TOKEN);
//            if (userId.equals(token)) {
//                // early return
//                log.warn(String.format(CharityConstants.LOG_USER_ALREADY_LOGIN, email));
//                return successReturn;
//            }

            // md5 incoming password
            String md5Password;
            try {
                // convert password into md5 code
                md5Password = MD5Util.md5(password);
            } catch (NoSuchAlgorithmException e) {
                log.error(CharityConstants.NO_SUCH_ALGORITHM_ERROR, e);
                throw new SystemInternalException();
            }

            if (md5Password.equals(userPassword)) {
                // correct && set to session
                httpSession.setAttribute(CharityConstants.HEADER_REQUEST_TOKEN, userId);
                log.info(String.format(CharityConstants.LOG_USER_LOGIN_SUCCESS, email));
                return successReturn;
            } else {
                // incorrect user password
                log.error(String.format(CharityConstants.LOG_INCORRECT_PASSWORD, email));
                return new ReturnStatus(CharityConstants.RETURN_EMAIL_OR_PASSWORD_ERROR,
                        CharityCodes.LOGIN_PASSWORD_ERROR, StatusType.FAIL);
            }
        } else {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST, email));
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_OR_PASSWORD_ERROR,
                    CharityCodes.LOGIN_EMAIL_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }
}
