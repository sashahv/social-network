package com.olekhv.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationRequest {
    private String email;
    private String password;
}
