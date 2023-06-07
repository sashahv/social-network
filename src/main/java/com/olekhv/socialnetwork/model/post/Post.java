package com.olekhv.socialnetwork.model.post;

import com.olekhv.socialnetwork.model.attachment.Attachment;
import com.olekhv.socialnetwork.model.comment.Comment;
import com.olekhv.socialnetwork.model.like.Like;
import com.olekhv.socialnetwork.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private String content;
    private LocalDateTime createdAt;
    private List<Like> likes;
    private List<Comment> comments;
    private List<Attachment> attachments;
    private User author;
}
