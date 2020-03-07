package com.forever17.project.charityquest.pojos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordCode {

    private List<Code> codes;

    /**
     * expire time in minutes
     */
    private int expire;

    /**
     * url of reset password page
     */
    private String baseUrl;

    /**
     * generate a new code for resetting password
     */
    public void generateCode() {

    }

    /**
     * test if code is valid
     *
     * @param code code for resetting password
     * @return return {@code true} if code is valid, otherwise return {@code false}
     */
    public boolean testCode(String code) {
        return true;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class Code {

        private String code;

        private LocalTime createTime;
    }
}
