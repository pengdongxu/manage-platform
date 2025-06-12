package com.coldlight.backend.security.principal;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.security.BaseUserPrincipal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 17:02
 * @description 小程序用户策略
 */
@Component
public class MiniAppUserPrincipalStrategy implements UserPrincipalStrategy {
    @Override
    public boolean supports(UserType userType) {
        return UserType.CUSER.equals(userType);
    }

    @Override
    public BaseUserPrincipal handle(Claims claims, HttpServletRequest request) {
        return null;
    }
}
