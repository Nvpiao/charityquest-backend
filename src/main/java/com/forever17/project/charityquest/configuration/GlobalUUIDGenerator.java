package com.forever17.project.charityquest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * UUID generator
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Configuration
public class GlobalUUIDGenerator {

    @Bean
    public UUID getUUIDBean() {
        return UUID.randomUUID();
    }
}
