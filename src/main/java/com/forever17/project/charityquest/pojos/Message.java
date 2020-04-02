package com.forever17.project.charityquest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.MessageType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id of message
     */
    @JsonProperty(value = "messageId")
    private String id;

    /**
     * id of charity, it is a foreign key
     */
    @NotBlank(message = CharityConstants.VALID_CHARITY_ID_BLANK_WARN)
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
     * latest sending time
     */
    private LocalDateTime sentTime;

    /**
     * subject of message
     */
    @NotBlank(message = CharityConstants.VALID_MESSAGE_SUBJECT_CANNOT_BLANK)
    private String subject;

    /**
     * content of message
     */
    @NotBlank(message = CharityConstants.VALID_MESSAGE_CONTENT_CANNOT_BLANK)
    private String content;

    /**
     * status of message. types: draft, sent, failed. default: draft.
     */
    private String status = MessageType.DRAFT.getName();

    /**
     * error message when it send failed.
     */
    private String error;

    public Message(String id, String status) {
        this(id, status, LocalDateTime.now());
    }

    public Message(String id, String status, LocalDateTime sentTime) {
        this.id = id;
        this.status = status;
        this.sentTime = sentTime;
    }
}