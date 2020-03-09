package com.forever17.project.charityquest;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main method entry
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@SpringBootApplication
@EnableEncryptableProperties
@EnableConfigurationProperties
@EnableSwagger2
@EnableSwaggerBootstrapUI
@MapperScan(basePackages = {"com.forever17.project.charityquest.mapper"})
public class CharityQuestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityQuestApplication.class, args);
    }

}
