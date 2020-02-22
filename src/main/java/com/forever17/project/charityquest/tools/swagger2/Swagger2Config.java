package com.forever17.project.charityquest.tools.swagger2;

import com.forever17.project.charityquest.constants.CharityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration of Swagger2
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(CharityConstants.SWAGGER2_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(CharityConstants.SWAGGER2_TITLE)
                .description(CharityConstants.SWAGGER2_DESC)
                .termsOfServiceUrl(CharityConstants.SWAGGER2_TERMS_OF_SERVICE)
                .contact(new Contact(CharityConstants.SWAGGER2_CONTACT_NAME,
                        CharityConstants.SWAGGER2_CONTACT_URL,
                        CharityConstants.SWAGGER2_CONTACT_EMAIL))
                .version(CharityConstants.SWAGGER2_VERSION)
                .build();
    }
}
