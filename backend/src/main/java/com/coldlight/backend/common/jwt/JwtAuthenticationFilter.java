package com.coldlight.backend.common.jwt;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.exception.UnauthorizedException;
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


    public JwtAuthenticationFilter(TokenServiceImpl tokenService, List<UserPrincipalStrategy> principalStrategies) {
        this.tokenService = tokenService;
        this.principalStrategies = principalStrategies;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (StringUtils.hasText(token)) {
            try {
                String requestClientType = request.getHeader("X-Client-Type");
                if (requestClientType == null){
                    writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "缺少请求参数");
                    return;
                }
                Claims claims = tokenService.parseToken(token);

                String tokneClientType = claims.get("clientType", String.class);
                if (!requestClientType.equals(tokneClientType)) {
                    writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token 与请求的 Client-Type 不一致");
                    return;
                }

                String userType = claims.get("userType", String.class);
                UserType userTypeEnum = UserType.valueOf(userType);
                BaseUserPrincipal userPrincipal = resolvePrincipal(userTypeEnum, claims, request);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception  e) {
                writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
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

    private void writeErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }
}
