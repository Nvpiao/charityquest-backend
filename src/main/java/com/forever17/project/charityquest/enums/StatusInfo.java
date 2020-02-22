package com.forever17.project.charityquest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * code of status:
 * 0   FAIL
 * 1   SUCCESS
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum StatusInfo {
    FAIL(0), SUCCESS(1);

    /**
     * code of status
     */
    private final int code;
}
