package com.forever17.project.charityquest.services;

import com.forever17.project.charityquest.pojos.entity.ReturnStatus;

/**
 * HomeService Interface
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 16 Mar 2020
 * @since 1.0
 */
public interface HomeService {

    /**
     * details of total donation and total donor.
     *
     * @return instance of ReturnStatus
     */
    ReturnStatus details();
}
