package com.forever17.project.charityquest.pojos.entity;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.enums.StatusInfo;
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
     * result data
     */
    private Object data;

    /**
     * state of request
     */
    private StatusInfo statusInfo = StatusInfo.SUCCESS;

    public ReturnStatus(String returnMsg) {
        this(returnMsg, CharityCodes.GLOBAL_SUCCESS);
    }

    public ReturnStatus(String returnMsg, String returnCode) {
        this(Collections.singletonList(returnMsg), returnCode);
    }

    public ReturnStatus(String returnMsg, String returnCode, StatusInfo statusInfo) {
        this(Collections.singletonList(returnMsg), returnCode, null, statusInfo);
    }

    public ReturnStatus(String returnMsg, String returnCode, Object data, StatusInfo statusInfo) {
        this(Collections.singletonList(returnMsg), returnCode, data, statusInfo);
    }

    public ReturnStatus(List<String> returnMsg, String returnCode, StatusInfo statusInfo) {
        this(returnMsg, returnCode, null, statusInfo);
    }
}
