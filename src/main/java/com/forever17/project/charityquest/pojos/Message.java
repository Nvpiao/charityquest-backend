package com.forever17.project.charityquest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}