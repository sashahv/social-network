package com.olekhv.socialnetwork.controller;

import com.olekhv.socialnetwork.dto.AuthenticationResponse;
import com.olekhv.socialnetwork.dto.AuthorizationRequest;
import com.olekhv.socialnetwork.dto.RegisterRequest;
import com.olekhv.socialnetwork.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authorize(@RequestBody AuthorizationRequest request){
        return ResponseEntity.ok(authenticationService.authorize(request));
    }
}
