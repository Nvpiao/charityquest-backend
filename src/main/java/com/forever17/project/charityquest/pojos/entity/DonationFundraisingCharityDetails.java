package com.forever17.project.charityquest.pojos.entity;

import com.forever17.project.charityquest.pojos.Donation;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * Combination of fundraising, donation and charity
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 2 Apr 2020
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DonationFundraisingCharityDetails extends FundraisingCharityDetails {
    /**
     * id of donation
     */
    private String donationId;

    /**
     * type of donation
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

    public DonationFundraisingCharityDetails(FundraisingCharityDetails fundraisingDetail, Donation donation) {
        BeanUtils.copyProperties(fundraisingDetail, this);
        BeanUtils.copyProperties(donation, this);
        this.donationId = donation.getId();
    }
}
