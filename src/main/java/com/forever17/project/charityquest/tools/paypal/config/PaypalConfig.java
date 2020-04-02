package com.forever17.project.charityquest.tools.paypal.config;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration of Paypal
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = CharityConstants.PAYPAL_PREFIX)
public class PaypalConfig {

    /**
     * id of client
     */
    private String clientId;

    /**
     * secret of client
     */
    private String clientSecret;

    /**
     * mode: live or sandbox
     */
    private String mode;

    /**
     * url of cancellation
     */
    private String cancelUrl;

    /**
     * url of succession for donation
     */
    private String successDonationUrl;

    /**
     * url of succession for fundraising
     */
    private String successFundraisingUrl;

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put(CharityConstants.PAYPAL_CONFIG_MODE, mode);
        return sdkConfig;
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        apiContext.setConfigurationMap(paypalSdkConfig());
        return apiContext;
    }
}
