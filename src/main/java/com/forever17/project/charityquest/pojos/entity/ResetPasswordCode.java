package com.forever17.project.charityquest.pojos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Code for resetting password
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 14 Feb 2020
 * @since 1.0
 */
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
    @Getter
    private String baseUrl;

    /**
     * generate a new code for resetting password
     *
     * @param email email address
     */
    public String generateCode(String email) {
        // get code
        String code = UUID.randomUUID().toString().replaceAll("-", "");

        // store code
        codes.add(new Code(code, email, LocalDateTime.now()));

        // return code
        return code;
    }

    /**
     * test if code is valid
     *
     * @param code  code for resetting password
     * @param email email for a specific code
     * @return return {@code true} if code is valid, otherwise return {@code false}
     */
    public boolean testCode(String code, String email) {
        // find code in list
        Optional<Code> anyCode = codes.stream()
                .filter(sourceCode -> sourceCode.getCode().equals(code))
                .findAny();

        if (anyCode.isPresent()) {
            Code sourceCode = anyCode.get();
            if (sourceCode.isAvailable()) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime createTime = sourceCode.getCreateTime();
                // calculate minutes
                long minutes = Duration.between(createTime, now).toMinutes();
                if (sourceCode.getEmail().equals(email) && minutes < expire) {
                    sourceCode.setAvailable(Boolean.FALSE);
                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class Code {

        /**
         * code for a specific email
         */
        private String code;

        /**
         * email
         */
        private String email;

        /**
         * if code available or not
         */
        private boolean available;

        /**
         * time of creation for code
         */
        private LocalDateTime createTime;

        public Code(String code, String email, LocalDateTime createTime) {
            this(code, email, Boolean.TRUE, createTime);
        }
    }
}
