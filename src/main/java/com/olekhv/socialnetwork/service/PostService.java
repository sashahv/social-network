package com.olekhv.socialnetwork.service;

import com.olekhv.socialnetwork.dto.CaptchaRequest;
import com.olekhv.socialnetwork.dto.PostCommentRequest;
import com.olekhv.socialnetwork.dto.PostDeletingRequest;
import com.olekhv.socialnetwork.dto.PostRequest;
import com.olekhv.socialnetwork.model.like.Like;
import com.olekhv.socialnetwork.model.post.Post;
import com.olekhv.socialnetwork.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final AuthenticationService authenticationService;

    public void createPost(PostRequest postRequest){
        log.info("here1");
        Post post = Post.builder()
                .content(postRequest.getContent())
                .createdAt(LocalDateTime.now())
                .likes(null)
                .comments(null)
                .author(authenticationService.getAuthorizedUserInfo())
                .build();

        postRepository.save(post);
    }

    public void editPost(Post post,
                         PostRequest postRequest){
        if(!post.getAuthor().equals(authenticationService.getAuthorizedUserInfo())){
            throw new RuntimeException("Post not found");
        }

        post.setContent(postRequest.getContent());
        post.setAttachments(postRequest.getAttachments());
        postRepository.save(post);
    }

    public void deletePost(PostDeletingRequest postDeletingRequest){
        CaptchaRequest captchaRequest = CaptchaRequest.builder()
                .requestedText(UUID.randomUUID().toString().replace("-", "").substring(0,6))
                .build();

        if(postDeletingRequest.getConfirmationCode().equals(captchaRequest.getRequestedText())){
            throw new IllegalArgumentException("Captcha not confirmed");
        }

        postRepository.delete(postDeletingRequest.getPost());
    }

    public void commentPost(PostCommentRequest postCommentRequest){
        postCommentRequest
                .getPost()
                .getComments()
                .add(postCommentRequest.getComment());
    }

    public void likePost(Post post){
        Like like = Like.builder()
                .user(authenticationService
                        .getAuthorizedUserInfo())
                .build();
        if(post.getLikes().contains(like)){
            post.getLikes().add(like);
        } else {
            post.getLikes().remove(like);
        }
    }

    public int getAmountOfLikes(Post post){
        return post.getLikes().size();
    }
}
