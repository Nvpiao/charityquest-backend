package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.StatusType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.mapper.CharityUserMapper;
import com.forever17.project.charityquest.mapper.FundraisingMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.CharityUserExample;
import com.forever17.project.charityquest.pojos.Fundraising;
import com.forever17.project.charityquest.pojos.FundraisingExample;
import com.forever17.project.charityquest.pojos.Message;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import com.forever17.project.charityquest.pojos.entity.FundraisingDetails;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.PublicUserService;
import com.forever17.project.charityquest.utils.EscapeUtils;
import com.forever17.project.charityquest.utils.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
     * fundraising mapper
     */
    private final FundraisingMapper fundraisingMapper;

    /**
     * Example of PublicUser class
     */
    private PublicUserExample publicUserExample;

    /**
     * Example of CharityUser class
     */
    private CharityUserExample charityUserExample;

    /**
     * Example of Fundraising class
     */
    private FundraisingExample fundraisingExample;

    {
        // static initialization
        publicUserExample = new PublicUserExample();
        charityUserExample = new CharityUserExample();
        fundraisingExample = new FundraisingExample();
    }

    @Autowired
    public PublicUserServiceImpl(HttpSession httpSession, PublicUserMapper publicUserMapper,
                                 CharityUserMapper charityUserMapper, FundraisingMapper fundraisingMapper) {
        this.httpSession = httpSession;
        this.publicUserMapper = publicUserMapper;
        this.charityUserMapper = charityUserMapper;
        this.fundraisingMapper = fundraisingMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus addUser(PublicUser publicUser) throws SystemInternalException {
        String userId = UUID.randomUUID().toString();
        String password = publicUser.getPassword();

        String md5Password = convertPasswordToMd5(password);
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

            // md5 incoming password
            String md5Password = convertPasswordToMd5(password);

            if (md5Password.equals(userPassword)) {
                // correct && set to session
                httpSession.setAttribute(CharityConstants.HEADER_REQUEST_TOKEN, userId);
                log.info(String.format(CharityConstants.LOG_USER_LOGIN_SUCCESS, email));
                // success return
                publicUser.setPassword(null);
                return new ReturnStatus(CharityConstants.RETURN_USER_LOGIN_SUCCESS, publicUser);
            } else {
                // incorrect user password
                log.error(String.format(CharityConstants.LOG_INCORRECT_PASSWORD, email));
                return new ReturnStatus(CharityConstants.RETURN_EMAIL_OR_PASSWORD_ERROR,
                        CharityCodes.LOGIN_PASSWORD_ERROR, StatusType.FAIL);
            }
        } else {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_EMAIL, email));
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_OR_PASSWORD_ERROR,
                    CharityCodes.LOGIN_EMAIL_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus changePassword(String publicId, String password) throws SystemInternalException {
        // query public user by id of public user
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(publicId);

        if (!Objects.isNull(publicUser)) {
            String md5Password = convertPasswordToMd5(password);

            if (md5Password.equals(publicUser.getPassword())) {
                // password can not be the same as previous.
                log.warn(String.format(CharityConstants.LOG_CHANGE_PASSWORD_DUPLICATE, publicId));
                return new ReturnStatus(CharityConstants.RETURN_PASSWORD_DUPLICATED_ERROR,
                        CharityCodes.CHANGE_PASSWORD_DUPLICATE, StatusType.WARN);
            }

            publicUserMapper.updateByPrimaryKeySelective(new PublicUser(publicId, md5Password));
            // success return
            return new ReturnStatus(CharityConstants.RETURN_CHANGE_PASSWORD_SUCCESS);

        } else {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, publicId));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    @Override
    public ReturnStatus showProfile(String id) {
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(id);
        if (Objects.isNull(publicUser)) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, id));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }
        // hide password
        publicUser.setPassword(null);
        // return success
        return new ReturnStatus(CharityConstants.RETURN_USER_INFO_GET_SUCCESS, publicUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus updateUser(PublicUser publicUser) {
        // set password && location null
        publicUser.setPassword(null);
        publicUser.setLocation(null);

        publicUser.setEmail(null);
        // update profile
        publicUserMapper.updateByPrimaryKeySelective(publicUser);
        return new ReturnStatus(CharityConstants.RETURN_USER_INFO_UPDATE_SUCCESS);
    }

    @Override
    public ReturnStatus showCharityList(String search, int pageNum, int pageSize) {

        // escape
        search = EscapeUtils.escapeMysql(search);

        // search criteria
        search = CharityConstants.SEARCH_WILD_CARD + search + CharityConstants.SEARCH_WILD_CARD;

        // charity example
        charityUserExample.clear();
        charityUserExample.createCriteria()
                .andNameLike(search);

        // page helper
        Page<Message> page = PageHelper.startPage(pageNum, pageSize);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        // assembly data
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_NUMS, page.getTotal());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_PAGES, page.getPages());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_NOW_PAGE, page.getPageNum());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_DATA, charityUsers);

        // return result
        return new ReturnStatus(CharityConstants.RETURN_CHARITY_LIST_GET_SUCCESS, dataMap);
    }

    @Override
    public ReturnStatus checkLink(String link) {
        // null check
        if (StringUtils.isBlank(link)) {
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_NOT_BLANK,
                    CharityCodes.PROPERTY_CAN_NOT_BE_BLANK, StatusType.FAIL);
        }

        fundraisingExample.clear();
        fundraisingExample.createCriteria()
                .andUrlEqualTo(link);
        List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);

        if (fundraisings.isEmpty()) {
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_BE_USED);
        } else {
            log.error(String.format(CharityConstants.LOG_FUNDRAISING_URL_CAN_NOT_BE_USED, link));
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_NOT_BE_USED,
                    CharityCodes.URL_CAN_NOT_BE_USED, StatusType.FAIL);
        }
    }

    @Override
    public ReturnStatus getFundraisingByLink(String link) {
        // null check
        if (StringUtils.isBlank(link)) {
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_NOT_BLANK,
                    CharityCodes.PROPERTY_CAN_NOT_BE_BLANK, StatusType.FAIL);
        }

        fundraisingExample.clear();
        fundraisingExample.createCriteria()
                .andUrlEqualTo(link);
        List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);
        if (!fundraisings.isEmpty()) {
            // at most one
            assert fundraisings.size() == 1;
            Fundraising fundraising = fundraisings.get(0);
            CharityUser charityUser = charityUserMapper.selectByPrimaryKey(fundraising.getCharityId());

            assert charityUser != null;
            return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_DETAILS_GET_SUCCESS,
                    new FundraisingDetails(fundraising, charityUser));
        } else {
            log.error(String.format(CharityConstants.LOG_FUNDRAISING_DOES_NOT_EXIST, link));
            return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_DOES_NOT_EXIST,
                    CharityCodes.FUNDRAISING_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    /**
     * convert password to md5 representation
     *
     * @param password password
     * @return encrypt password with md5
     * @throws SystemInternalException system internal exception
     */
    private String convertPasswordToMd5(String password) throws SystemInternalException {
        // md5 incoming password
        String md5Password;
        try {
            // convert password into md5 code
            md5Password = MD5Util.md5(password);
        } catch (NoSuchAlgorithmException e) {
            log.error(CharityConstants.NO_SUCH_ALGORITHM_ERROR, e);
            throw new SystemInternalException(CharityConstants.NO_SUCH_ALGORITHM_ERROR);
        }
        return md5Password;
    }
}
