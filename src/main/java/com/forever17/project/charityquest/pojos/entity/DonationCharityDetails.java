package com.forever17.project.charityquest.pojos.entity;

import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.Donation;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * Combination of donation and charity
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 2 Apr 2020
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DonationCharityDetails {
    /**
     * id of charity
     */
    private String charityId;

    /**
     * id of public user
     */
    private String publicId;

    /**
     * id of donation
     */
    private String donationId;

    /**
     * donation or fundraising. default donation
     */
    private String type;

    /**
     * type of donation, types: once, weekly, monthly, quarterly, yearly, default: once
     */
    private String donateType;

    /**
     * money of donation
     */
    private Double money;

    /**
     * time of donation
     */
    private LocalDateTime time;

    /**
     * status of recursive donation, type: enabled, cancelled
     */
    private String status;

    /**
     * name of charity
     */
    private String name;

    /**
     * avatar of charity
     */
    private String photo;

    /**
     * number of charity
     */
    private String number;

    /**
     * description of charity
     */
    private String description;

    /**
     * case photo of charity
     */
    private String casePhoto;

    /**
     * case video address of charity
     */
    private String caseVideo;

    /**
     * case description of charity
     */
    private String caseDesc;

    public DonationCharityDetails(Donation donation, CharityUser charityUser) {
        BeanUtils.copyProperties(charityUser, this);
        BeanUtils.copyProperties(donation, this);
        this.donationId = donation.getId();
    }
}
