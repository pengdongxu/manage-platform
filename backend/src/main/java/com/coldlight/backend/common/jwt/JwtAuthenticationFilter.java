package com.coldlight.backend.common.jwt;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.exception.UnauthorizedException;
import com.coldlight.backend.config.SecurityProperties;
import com.coldlight.backend.security.BaseUserPrincipal;
import com.coldlight.backend.security.principal.UserPrincipalStrategy;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/10 10:28
 * @description JWT拦截器
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenServiceImpl tokenService;
    private final List<UserPrincipalStrategy> principalStrategies;
    private final SecurityProperties securityProperties;


    public JwtAuthenticationFilter(TokenServiceImpl tokenService,
                                   List<UserPrincipalStrategy> principalStrategies,
                                   SecurityProperties securityProperties) {
        this.tokenService = tokenService;
        this.principalStrategies = principalStrategies;
        this.securityProperties = securityProperties;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestClientType = request.getHeader("X-Client-Type");

        // 客户端类型缺失，直接返回
        if (!StringUtils.hasText(requestClientType)) {
            returnError(response, HttpServletResponse.SC_BAD_REQUEST, "缺少请求头参数 X-Client-Type");
            return;
        }

        // 公共路径直接放行
        if (isPublicPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);

        if (!StringUtils.hasText(token)) {
            returnError(response, HttpServletResponse.SC_UNAUTHORIZED, "未提供令牌");
            return;
        }

        try {
            Claims claims = tokenService.parseToken(token);

            String tokenClientType = claims.get("clientType", String.class);
            if (!requestClientType.equals(tokenClientType)) {
                returnError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token 与请求头 Client-Type 不一致");
                return;
            }

            String userType = claims.get("userType", String.class);
            UserType userTypeEnum = UserType.valueOf(userType);

            BaseUserPrincipal principal = resolvePrincipal(userTypeEnum, claims, request);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            returnError(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }


    private boolean isPublicPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        return securityProperties.getGlobalWhiteList().stream()
                .anyMatch(p -> path.matches(p.replace("**", ".*")));
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private BaseUserPrincipal resolvePrincipal(UserType userType, Claims claims, HttpServletRequest request) {
        return principalStrategies.stream()
                .filter(s -> s.supports(userType))
                .findFirst()
                .orElseThrow(() -> new UnauthorizedException("不支持的用户类型"))
                .handle(claims, request);
    }

    private void returnError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }
}
