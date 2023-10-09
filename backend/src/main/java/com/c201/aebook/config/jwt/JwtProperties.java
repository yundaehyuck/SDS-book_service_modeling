package com.c201.aebook.config.jwt;

public interface JwtProperties {

    String TOKEN_PREFIX = "Bearer "; //Token 앞에 붙는 prefix
    String AUTHORIZATION_HEADER = "Authorization"; //Header Key
    String REFRESH_HEADER = "Refresh";

}
