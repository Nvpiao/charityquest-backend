package com.forever17.project.charityquest.pojos.entity;

import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.Fundraising;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Combination of fundraising and chairty
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 21 Mar 2020
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class FundraisingDetails {
    /**
     * id of fundraising.
     */
    private String fundraisingId;

    /**
     * id of charity.
     */
    private String charityId;

    /**
     * url for fundraising
     */
    private String url;

    /**
     * start time of fundraising.
     */
    private LocalDateTime startTime;

    /**
     * end time of fundraising.
     */
    private LocalDateTime endTime;

    /**
     * how much do this project raise for now.
     */
    private Integer raiseMoney;

    /**
     * target of fundraising
     */
    private Integer targetMoney;

    /**
     * description for this fundraising
     */
    private String fundraisingDesc;

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
    private String charityDesc;

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

    public FundraisingDetails(Fundraising fundraising, CharityUser charityUser) {
        this.fundraisingId = fundraising.getId();
        this.charityId = charityUser.getId();
        this.url = fundraising.getUrl();
        this.startTime = fundraising.getStartTime();
        this.endTime = fundraising.getEndTime();
        this.raiseMoney = fundraising.getRaiseMoney();
        this.targetMoney = fundraising.getTargetMoney();
        this.fundraisingDesc = fundraising.getDescription();
        this.name = charityUser.getName();
        this.photo = charityUser.getPhoto();
        this.number = charityUser.getNumber();
        this.charityDesc = charityUser.getDescription();
        this.casePhoto = charityUser.getCasePhoto();
        this.caseVideo = charityUser.getCaseVideo();
        this.caseDesc = charityUser.getCaseDesc();
    }
}
