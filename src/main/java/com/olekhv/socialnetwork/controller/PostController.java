package com.olekhv.socialnetwork.controller;

import com.olekhv.socialnetwork.dto.PostRequest;
import com.olekhv.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping
    public String hello(){
        return "HELLO";
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest){
        log.info("hello");
        postService.createPost(postRequest);
        return new ResponseEntity<>("Post successfully created", HttpStatus.CREATED);
    }
}
