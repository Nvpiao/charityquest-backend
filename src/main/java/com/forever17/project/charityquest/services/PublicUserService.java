package com.forever17.project.charityquest.services;

import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;

/**
 * PublicUserService Interface
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
public interface PublicUserService {
    /**
     * add a new public user into database
     *
     * @param publicUser object of public user
     * @return instance of ReturnStatus
     * @throws SystemInternalException exception
     */
    ReturnStatus addUser(PublicUser publicUser) throws SystemInternalException;

    /**
     * check whether email has already been registered by other public or charity.
     *
     * @param email email address
     * @return instance of ReturnStatus
     */
    ReturnStatus checkEmail(String email);

    /**
     * check whether user exist and password is correct
     *
     * @param email    email of public user
     * @param password password of user
     * @return instance of ReturnStatus
     */
    ReturnStatus signIn(String email, String password) throws SystemInternalException;

    /**
     * change password of public user
     *
     * @param publicId id of public user
     * @param password password of public user
     * @return instance of ReturnStatus
     */
    ReturnStatus changePassword(String publicId, String password) throws SystemInternalException;
}
