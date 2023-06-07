package com.olekhv.socialnetwork.model.comment;

import com.olekhv.socialnetwork.model.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    private String id;
    private String content;
    private LocalDateTime createdAt;
    private User user;
}
