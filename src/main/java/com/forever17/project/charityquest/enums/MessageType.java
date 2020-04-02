package com.forever17.project.charityquest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * type of message:
 * 0   DRAFT
 * 1   SENT
 * 2   FAILED
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 10 Mar 2020
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum MessageType {
    DRAFT(0), SENT(1), FAILED(2);

    /**
     * code of message type
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
