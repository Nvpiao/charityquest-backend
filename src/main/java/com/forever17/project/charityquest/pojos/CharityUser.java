package com.forever17.project.charityquest.pojos;

import lombok.Data;

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
public class CharityUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id of charity
     */
    private String id;
    /**
     * email of charity
     */
    private String email;
    /**
     * password of charity
     */
    private String password;
    /**
     * name of charity
     */
    private String name;
    /**
     * number of charity
     */
    private String number;
    /**
     * avatar of charity
     */
    private String photo;
    /**
     * photo of charity case
     */
    private String casePhoto;
    /**
     * description of charity case
     */
    private String description;
    /**
     * description of project
     */
    private String projectDesc;
    /**
     * case video address of project
     */
    private String caseVideo;

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
        CharityUser other = (CharityUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
                && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
                && (this.getCasePhoto() == null ? other.getCasePhoto() == null : this.getCasePhoto().equals(other.getCasePhoto()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getProjectDesc() == null ? other.getProjectDesc() == null : this.getProjectDesc().equals(other.getProjectDesc()))
                && (this.getCaseVideo() == null ? other.getCaseVideo() == null : this.getCaseVideo().equals(other.getCaseVideo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getCasePhoto() == null) ? 0 : getCasePhoto().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getProjectDesc() == null) ? 0 : getProjectDesc().hashCode());
        result = prime * result + ((getCaseVideo() == null) ? 0 : getCaseVideo().hashCode());
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
                ", name=" + name +
                ", number=" + number +
                ", photo=" + photo +
                ", casePhoto=" + casePhoto +
                ", description=" + description +
                ", projectDesc=" + projectDesc +
                ", caseVideo=" + caseVideo +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}