package com.c201.aebook.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider; //JWT 토큰 생성 관리
    private final RedisTemplate redisTemplate;

    @Override
    public void configure(HttpSecurity builder) {
        JwtFilter jwtTokenFilter = new JwtFilter(jwtTokenProvider, redisTemplate); // 커스텀 필터인 JwtFilter 생성
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class); // 지정된 필터 앞에 커스텀 필터 추가(먼저 실행)
    }

}
