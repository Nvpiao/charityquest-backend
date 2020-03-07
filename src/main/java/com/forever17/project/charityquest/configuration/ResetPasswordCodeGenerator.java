package com.forever17.project.charityquest.configuration;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.pojos.entity.ResetPasswordCode;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * reset password code generator
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = CharityConstants.CODE_PREFIX)
public class ResetPasswordCodeGenerator {

    @Setter
    @Getter
    private int expire;

    @Setter
    @Getter
    private String baseUrl;

    @Bean
    public ResetPasswordCode getResetPasswordCodeBean() {
        return ResetPasswordCode.builder()
                .codes(Lists.newArrayList())
                .expire(expire)
                .baseUrl(baseUrl)
                .build();
    }
}
