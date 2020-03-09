package com.forever17.project.charityquest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Donation POJO
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
public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id of donation
     */
    @JsonProperty(value = "donationId")
    private String id;

    /**
     * id of public (who donated)
     */
    private String publicId;

    /**
     * donation or fundraising. default donation
     */
    private String type;

    /**
     * id of charity (to who)
     */
    private String charityId;

    /**
     * id of fundraising
     */
    private String fundraisingId;

    /**
     * type of donation, types: once, weekly, monthly, quarterly, yearly, default: once
     */
    private String donateType;

    /**
     * money of donation
     */
    private Integer money;

    /**
     * time of donation
     */
    private LocalDateTime time;
}