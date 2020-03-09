package com.forever17.project.charityquest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * type of result status:
 * 0   PUBLIC
 * 1   CHARITY
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 7 Mar 2020
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum UserType {
    PUBLIC(0), CHARITY(1);

    private static UserType[] list = UserType.values();
    /**
     * code of user
     */
    private int code;

    public static UserType getUserType(int i) {
        return list[i];
    }
}
