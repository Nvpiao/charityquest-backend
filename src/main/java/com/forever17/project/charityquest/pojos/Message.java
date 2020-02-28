package com.forever17.project.charityquest.pojos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Message POJO
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id of message
     */
    private String id;
    /**
     * id of charity, it is a foreign key
     */
    private String charityId;
    /**
     * time of creation
     */
    private LocalDateTime createTime;
    /**
     * time of modification
     */
    private LocalDateTime modifyTime;
    /**
     * lastest sending time
     */
    private LocalDateTime sentTime;
    /**
     * subject of message
     */
    private String subject;
    /**
     * content of message
     */
    private String content;
    /**
     * status of message. types: draft, sent, failed. default: draft.
     */
    private String status;
    /**
     * error message when it send failed.
     */
    private String error;

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
        Message other = (Message) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCharityId() == null ? other.getCharityId() == null : this.getCharityId().equals(other.getCharityId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
                && (this.getSentTime() == null ? other.getSentTime() == null : this.getSentTime().equals(other.getSentTime()))
                && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getError() == null ? other.getError() == null : this.getError().equals(other.getError()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCharityId() == null) ? 0 : getCharityId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getSentTime() == null) ? 0 : getSentTime().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getError() == null) ? 0 : getError().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", charityId=" + charityId +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", sentTime=" + sentTime +
                ", subject=" + subject +
                ", content=" + content +
                ", status=" + status +
                ", error=" + error +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}