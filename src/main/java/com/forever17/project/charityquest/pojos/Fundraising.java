package com.forever17.project.charityquest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forever17.project.charityquest.constants.CharityConstants;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Fundraising POJO
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Fundraising implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id of fundraising.
     */
    @JsonProperty(value = "fundraisingId")
    private String id;

    /**
     * id of charity.
     */
    private String charityId;

    /**
     * url for fundraising
     */
    @NotBlank(message = CharityConstants.VALID_URL_BLANK_WARN)
    private String url;

    /**
     * start time of fundraising.
     */
    @NotBlank(message = CharityConstants.VALID_START_TIME_BLANK_WARN)
    private LocalDateTime startTime;

    /**
     * end time of fundraising.
     */
    @NotBlank(message = CharityConstants.VALID_END_TIME_BLANK_WARN)
    private LocalDateTime endTime;

    /**
     * how much do this project raise for now.
     */
    private Integer raiseMoney;

    /**
     * target of fundraising
     */
    @Min(value = 1, message = CharityConstants.VALID_UNDER_MIN_MONEY_WARN)
    @Max(value = 500, message = CharityConstants.VALID_UPPER_MAX_MONEY_WARN)
    private Integer targetMoney;

    /**
     * description for this fundraising
     */
    private String description;
}