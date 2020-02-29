package com.forever17.project.charityquest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * type of result status:
 * 0   FAIL
 * 1   SUCCESS
 * 2   WARN
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum StatusType {
    FAIL(0), SUCCESS(1), WARN(2);

    /**
     * code of status
     */
    private final int code;
}
