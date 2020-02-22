package com.forever17.project.charityquest.services;

import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.utils.ReturnStatus;

/**
 * PublicUserService Interface
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
public interface PublicUserService {
    ReturnStatus addUser(PublicUser publicUser) throws SystemInternalException;
}
