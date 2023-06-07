package com.olekhv.socialnetwork.dto;

import com.olekhv.socialnetwork.model.comment.Comment;
import com.olekhv.socialnetwork.model.post.Post;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class PostCommentRequest {
    private Post post;
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommentRequest that = (PostCommentRequest) o;
        return post.equals(that.post) && comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, comment);
    }
}
