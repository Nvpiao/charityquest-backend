package com.forever17.project.charityquest.services;

import com.forever17.project.charityquest.enums.UserType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;

/**
 * CommonUserService Interface
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 7 Mar 2020
 * @since 1.0
 */
public interface CommonUserService {

    /**
     * send reset password email with code.
     *
     * @param email email
     * @return instance of ReturnStatus
     */
    ReturnStatus sendResetPassword(String email) throws SystemInternalException;

    /**
     * @param email    email address
     * @param password new password
     * @param code     code for resetting password
     * @param userType type of user
     * @return instance of ReturnStatus
     * @throws SystemInternalException system internal exception
     */
    ReturnStatus resetPassword(String email, String password, String code, UserType userType)
            throws SystemInternalException;
}
