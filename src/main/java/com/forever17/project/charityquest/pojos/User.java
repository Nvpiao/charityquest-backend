package com.forever17.project.charityquest.pojos;

import com.forever17.project.charityquest.constants.CharityConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    public String id;

    /**
     * email address of user
     */
    @Email(message = CharityConstants.VALID_EMAIL_NOT_VALID_WARN)
    public String email;

    /**
     * password of user
     */
    @NotBlank(message = CharityConstants.VALID_PASSWORD_BLANK_WARN)
    public String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
