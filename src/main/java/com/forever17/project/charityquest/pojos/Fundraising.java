package com.forever17.project.charityquest.pojos;

import com.forever17.project.charityquest.constants.CharityConstants;
import io.swagger.annotations.ApiModel;
import lombok.Data;

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
@ApiModel
public class Fundraising implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id of fundraising.
     */
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Fundraising other = (Fundraising) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCharityId() == null ? other.getCharityId() == null : this.getCharityId().equals(other.getCharityId()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getRaiseMoney() == null ? other.getRaiseMoney() == null : this.getRaiseMoney().equals(other.getRaiseMoney()))
                && (this.getTargetMoney() == null ? other.getTargetMoney() == null : this.getTargetMoney().equals(other.getTargetMoney()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCharityId() == null) ? 0 : getCharityId().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getRaiseMoney() == null) ? 0 : getRaiseMoney().hashCode());
        result = prime * result + ((getTargetMoney() == null) ? 0 : getTargetMoney().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", charityId=").append(charityId);
        sb.append(", url=").append(url);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", raiseMoney=").append(raiseMoney);
        sb.append(", targetMoney=").append(targetMoney);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}