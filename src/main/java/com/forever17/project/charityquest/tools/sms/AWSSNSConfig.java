package com.forever17.project.charityquest.tools.sms;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuration of SNS from aws
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 23 Mar 2020
 * @since 1.0
 */
@Configuration
public class AWSSNSConfig {
    @Bean
    @Primary
    public AWSCredentialsProvider getCredentialsProvider() {
        return new ClasspathPropertiesFileCredentialsProvider();
    }
}
