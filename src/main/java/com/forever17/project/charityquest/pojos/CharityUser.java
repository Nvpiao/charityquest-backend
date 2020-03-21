package com.forever17.project.charityquest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forever17.project.charityquest.constants.CharityConstants;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * CharityUser POJO
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.1
 * @date 27 Feb 2020
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class CharityUser extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id of charity
     */
    @JsonProperty(value = "charityId")
    private String id;

    /**
     * name of charity
     */
    @NotBlank(message = CharityConstants.VALID_CHARITY_NAME_BLANK_WARN)
    private String name;

    /**
     * avatar of charity
     */
    private String photo;

    /**
     * number of charity
     */
    @NotBlank(message = CharityConstants.VALID_CHARITY_NO_BLANK_WARN)
    private String number;

    /**
     * description of charity
     */
    private String description;

    /**
     * case photo of charity
     */
    private String casePhoto;

    /**
     * case video address of charity
     */
    private String caseVideo;

    /**
     * case description of charity
     */
    private String caseDesc;

    public CharityUser(String id, String password) {
        super(id, password);
        this.id = id;
    }
}