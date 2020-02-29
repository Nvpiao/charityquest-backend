package com.forever17.project.charityquest.configuration;

import com.forever17.project.charityquest.pojos.entity.ResetPasswordCode;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
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
public class ResetPasswordCodeGenerator {

    @Value("${system.code.expire}")
    private int expire;

    @Bean
    public ResetPasswordCode getResetPasswordCodeBean() {
        return ResetPasswordCode.builder()
                .codes(Lists.newArrayList())
                .expire(expire)
                .build();
    }
}
