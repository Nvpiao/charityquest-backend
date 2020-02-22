package com.forever17.project.charityquest.pojos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * PublicUser POJO
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Data
public class PublicUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    @NotBlank(message = "login name can not be blanked!")
    private String loginName;
    @NotBlank(message = "password can not be blanked!")
    private String password;
    private String name;
    private Integer telPre;
    private Integer tel;
    private Integer gender;
    @Email(message = "Email not valid.")
    private String email;
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
                && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTelPre() == null ? other.getTelPre() == null : this.getTelPre().equals(other.getTelPre()))
                && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
                && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTelPre() == null) ? 0 : getTelPre().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", loginName=" + loginName +
                ", password=" + password +
                ", name=" + name +
                ", telPre=" + telPre +
                ", tel=" + tel +
                ", gender=" + gender +
                ", email=" + email +
                ", photo=" + photo +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}