package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.StatusInfo;
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

    @Autowired
    private UUID uuid;

    @Autowired
    private PublicUserMapper publicUserMapper;

    @Autowired
    public CharityUserMapper charityUserMapper;

    /**
     * Example of PublicUser class
     */
    PublicUserExample publicUserExample;

    /**
     * Criteria of PublicUser class
     */
    PublicUserExample.Criteria publicUserCriteria;

    /**
     * Example of CharityUser class
     */
    CharityUserExample charityUserExample;

    /**
     * Criteria of CharityUser class
     */
    CharityUserExample.Criteria charityUserCriteria;

    {
        // static initialization
        publicUserExample = new PublicUserExample();
        publicUserCriteria = publicUserExample.createCriteria();

        charityUserExample = new CharityUserExample();
        charityUserCriteria = charityUserExample.createCriteria();
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
        if (emailStatus.getStatusInfo().equals(StatusInfo.WARN)) {
            return emailStatus;
        }

        // insert into database
        publicUserMapper.insert(publicUser);

        // return success result status
        return new ReturnStatus(CharityConstants.RETURN_USER_ADDED);
    }

    @Override
    public ReturnStatus checkEmail(String email) {
        // query public user by email address
        publicUserExample.clear();
        publicUserCriteria.andEmailEqualTo(email);
        List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

        // query charity user by email address
        charityUserExample.clear();
        charityUserCriteria.andEmailEqualTo(email);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        // judge
        if (publicUsers.isEmpty() && charityUsers.isEmpty()) {
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_CAN_BE_USED);
        } else {
            log.warn(CharityConstants.RETURN_EMAIL_CAN_NOT_BE_USED);
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_CAN_NOT_BE_USED,
                    CharityCodes.EMAIL_ADDRESS_CAN_NOT_BE_USED, StatusInfo.WARN);
        }
    }
}
