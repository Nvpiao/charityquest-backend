package com.forever17.project.charityquest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forever17.project.charityquest.constants.CharityConstants;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class PublicUser extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id of public user
     */
    @JsonProperty(value = "publicId")
    private String id;

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
    private String tel;

    /**
     * avatar of public user
     */
    private String photo;

    /**
     * location of public user
     */
    private String location;

    public PublicUser(String id, String password) {
        super(id, password);
        this.id = id;
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
                ", location=" + location +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}