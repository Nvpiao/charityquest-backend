package com.forever17.project.charityquest.services;

import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;

/**
 * CharityUserService Interface
 *
 * @author Yuhao Hu (Yhu74@sheffield.ac.uk)
 * @version 1.0
 * @date 07/03/2020
 * @since 1.0
 */
public interface CharityUserService {
    /**
     * add a new Charity user into database
     *
     * @param charityUser object of public user
     * @return instance of ReturnStatus
     * @throws SystemInternalException exception
     */
    ReturnStatus addUser(CharityUser charityUser) throws SystemInternalException;

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
     * @param charityId id of charity user
     * @param password  password of charity user
     * @return instance of ReturnStatus
     */
    ReturnStatus changePassword(String charityId, String password) throws SystemInternalException;

    /**
     * show the profile of charity user
     *
     * @param id id of charity user
     * @return instance of ReturnStatus
     */
    ReturnStatus showProfile(String id);

    /**
     * update profile of charity user
     *
     * @param charityUser instance of public user
     * @return instance of ReturnStatus
     */
    ReturnStatus updateUser(CharityUser charityUser);
}
