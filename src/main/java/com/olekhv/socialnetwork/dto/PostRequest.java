package com.olekhv.socialnetwork.dto;

import com.olekhv.socialnetwork.model.attachment.Attachment;
import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    private String content;
    private List<Attachment> attachments;
}
