package com.forever17.project.charityquest.services;

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
    ReturnStatus sendResetPassword(String email);
}
