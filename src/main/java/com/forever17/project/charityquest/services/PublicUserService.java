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
     * @return class of ReturnStatus
     * @throws SystemInternalException exception
     */
    ReturnStatus addUser(PublicUser publicUser) throws SystemInternalException;

    /**
     * check whether email has already been registered by other public or charity.
     *
     * @param email email address
     * @return class of ReturnStatus
     */
    ReturnStatus checkEmail(String email);
}
