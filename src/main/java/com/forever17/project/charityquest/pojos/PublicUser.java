package com.forever17.project.charityquest.pojos;

import com.forever17.project.charityquest.constants.CharityConstants;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * PublicUser POJO
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.1
 * @date 15 Feb 2020
 * @since 1.0
 */
@Data
@ApiModel
public class PublicUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id of public user
     */
    private String id;
    /**
     * email address of public user
     */
    @Email(message = CharityConstants.VALID_EMAIL_NOT_VALID_WARN)
    private String email;
    /**
     * password of public user
     */
    @NotBlank(message = CharityConstants.VALID_PASSWORD_BLANK_WARN)
    private String password;
    /**
     * title of public user, content:'Mr', 'Mrs', 'Miss', 'Ms', 'Dr', 'Other'. default: Mr
     */
    @NotBlank(message = CharityConstants.VALID_TITLE_BLANK_WARN)
    private String title;
    /**
     * first name of public user
     */
    private String firstName;
    /**
     * last name of public user
     */
    private String lastName;
    /**
     * telephone country code of public user
     */
    private String telPre;
    /**
     * telephone number of public user
     */
    @NotBlank(message = CharityConstants.VALID_TEL_BLANK_WARN)
    private String tel;
    /**
     * avatar of public user
     */
    private String photo;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PublicUser other = (PublicUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
                && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
                && (this.getTelPre() == null ? other.getTelPre() == null : this.getTelPre().equals(other.getTelPre()))
                && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
                && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getTelPre() == null) ? 0 : getTelPre().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", email=" + email +
                ", password=" + password +
                ", title=" + title +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", telPre=" + telPre +
                ", tel=" + tel +
                ", photo=" + photo +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}