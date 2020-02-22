package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.constants.CharityErrorCodes;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.services.PublicUserService;
import com.forever17.project.charityquest.utils.MD5Util;
import com.forever17.project.charityquest.utils.ReturnStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
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

        //TODO loginName check

        // insert into database
        publicUserMapper.insert(publicUser);

        // return success result status
        return new ReturnStatus(CharityConstants.RETURN_USER_ADDED,
                CharityErrorCodes.GLOBAL_SUCCESS);
    }
}
