package com.olekhv.socialnetwork.dto;

import com.olekhv.socialnetwork.model.post.Post;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class PostDeletingRequest {
    private Post post;
    private String confirmationCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDeletingRequest that = (PostDeletingRequest) o;
        return post.equals(that.post) && confirmationCode.equals(that.confirmationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, confirmationCode);
    }
}
