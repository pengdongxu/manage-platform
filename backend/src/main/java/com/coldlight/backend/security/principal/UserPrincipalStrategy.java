package com.coldlight.backend.security.principal;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.security.BaseUserPrincipal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 16:51
 * @description 获取用户信息
 */
public interface UserPrincipalStrategy {

    boolean supports(UserType userType);

    BaseUserPrincipal handle(Claims claims, HttpServletRequest request);
}
