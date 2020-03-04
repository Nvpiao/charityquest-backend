package com.forever17.project.charityquest.pojos.entity;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * Return Status of Project
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ReturnStatus {
    /**
     * messages
     */
    @NonNull
    private List<String> returnMsgs;

    /**
     * result error code
     */
    @NonNull
    private String returnCode;

    /**
     * state of request
     */
    private StatusType statusType = StatusType.SUCCESS;

    /**
     * result data
     */
    private Object data;

    // success
    public ReturnStatus(String returnMsg) {
        this(returnMsg, CharityCodes.GLOBAL_SUCCESS);
    }

    // success
    public ReturnStatus(String returnMsg, String returnCode) {
        this(Collections.singletonList(returnMsg), returnCode);
    }

    // success
    public ReturnStatus(String returnMsg, Object data) {
        this(Collections.singletonList(returnMsg), CharityCodes.GLOBAL_SUCCESS, StatusType.SUCCESS, data);
    }

    public ReturnStatus(String returnMsg, String returnCode, StatusType statusType) {
        this(Collections.singletonList(returnMsg), returnCode, statusType, null);
    }

    public ReturnStatus(String returnMsg, String returnCode, StatusType statusType, Object data) {
        this(Collections.singletonList(returnMsg), returnCode, statusType, data);
    }

    public ReturnStatus(List<String> returnMsg, String returnCode, StatusType statusType) {
        this(returnMsg, returnCode, statusType, null);
    }
}
