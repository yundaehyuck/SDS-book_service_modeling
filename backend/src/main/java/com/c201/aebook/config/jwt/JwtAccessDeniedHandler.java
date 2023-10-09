package com.c201.aebook.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> errorResult = new HashMap<>();

        // 에러 정보 저장
        errorResult.put("timestamp", LocalDateTime.now().toString());
        errorResult.put("status", 403);
        errorResult.put("error", "Unauthorized");
        errorResult.put("message", "권한 정보가 올바르지 않습니다.");
        String result = objectMapper.writeValueAsString(errorResult);

        // response에 에러 정보 담기
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result);
    }
}
