package com.c201.aebook.api.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenSO {

    private String accessToken; // access Token
    private String refreshToken; // refresh Token

}
