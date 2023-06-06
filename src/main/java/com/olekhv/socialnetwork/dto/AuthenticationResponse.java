package com.olekhv.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
}
