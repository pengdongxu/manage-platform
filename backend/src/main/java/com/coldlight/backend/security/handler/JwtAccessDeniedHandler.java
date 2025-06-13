package com.coldlight.backend.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/13 14:19
 * @description 自定义权限拒绝处理器
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":\"FORBIDDEN\", \"message\":\"权限不足\"}");

    }
}
