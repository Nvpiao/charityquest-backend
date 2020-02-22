package com.forever17.project.charityquest.utils;

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
    @NonNull
    private List<String> returnMsgs;

    @NonNull
    private String returnCode;

    private StatusInfo statusInfo = StatusInfo.SUCCESS;

    public ReturnStatus(String returnUserAdded, String returnCode) {
        this(Collections.singletonList(returnUserAdded), returnCode);
    }

    public ReturnStatus(String returnUserAdded, String returnCode, StatusInfo statusInfo) {
        this(Collections.singletonList(returnUserAdded), returnCode, statusInfo);
    }
}
