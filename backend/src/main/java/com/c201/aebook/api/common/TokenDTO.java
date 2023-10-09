package com.c201.aebook.api.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {
    private String AuthorizationHeader; // Authorization
    private String RefreshHeader; // Refresh
    private String grantType; // Bearer
    private String accessToken; // access Token
    private long accessTokenExpiresIn; // access Token 만료 시간
    private String refreshToken; // refresh Token
}
