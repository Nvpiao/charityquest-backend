package com.forever17.project.charityquest.configuration;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.StatusInfo;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * System internal error bean
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Configuration
public class SystemInternalError {

    @Bean
    public ReturnStatus getInternalErrorBean() {
        return new ReturnStatus(CharityConstants.RETURN_SYSTEM_INTERNAL_ERROR,
                CharityCodes.GLOBAL_SYSTEM_INTERNAL_ERROR, StatusInfo.FAIL);
    }
}
