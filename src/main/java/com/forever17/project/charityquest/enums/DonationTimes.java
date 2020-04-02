package com.forever17.project.charityquest.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * times of donation:
 * 0   ONCE
 * 1   WEEKLY
 * 2   MONTHLY
 * 3   QUARTERLY
 * 4   YEARLY
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 2 Apr 2020
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum DonationTimes {
    ONCE(0),
    WEEKLY(1),
    MONTHLY(2),
    QUARTERLY(3),
    YEARLY(4);

    /**
     * code of donation times
     */
    private int code;
}
