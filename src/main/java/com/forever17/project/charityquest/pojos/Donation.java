package com.forever17.project.charityquest.pojos;

import lombok.Data;

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
public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id of donation
     */
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
        Donation other = (Donation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPublicId() == null ? other.getPublicId() == null : this.getPublicId().equals(other.getPublicId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getCharityId() == null ? other.getCharityId() == null : this.getCharityId().equals(other.getCharityId()))
                && (this.getFundraisingId() == null ? other.getFundraisingId() == null : this.getFundraisingId().equals(other.getFundraisingId()))
                && (this.getDonateType() == null ? other.getDonateType() == null : this.getDonateType().equals(other.getDonateType()))
                && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPublicId() == null) ? 0 : getPublicId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCharityId() == null) ? 0 : getCharityId().hashCode());
        result = prime * result + ((getFundraisingId() == null) ? 0 : getFundraisingId().hashCode());
        result = prime * result + ((getDonateType() == null) ? 0 : getDonateType().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", publicId=" + publicId +
                ", type=" + type +
                ", charityId=" + charityId +
                ", fundraisingId=" + fundraisingId +
                ", donateType=" + donateType +
                ", money=" + money +
                ", time=" + time +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}