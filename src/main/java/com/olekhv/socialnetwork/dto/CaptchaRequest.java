package com.olekhv.socialnetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaptchaRequest {
    private String requestedText;
}
