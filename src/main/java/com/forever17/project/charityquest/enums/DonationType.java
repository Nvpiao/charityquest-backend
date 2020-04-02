package com.forever17.project.charityquest.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * type of donation:
 * 0   DONATION
 * 1   FUNDRAISING
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 10 Mar 2020
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum DonationType {
    DONATION(0), FUNDRAISING(1);

    /**
     * code of donation type
     */
    private int code;

    /**
     * get name and to lowercase
     *
     * @return name of enum with lowercase
     */
    public String getName() {
        return this.name().toLowerCase();
    }
}
