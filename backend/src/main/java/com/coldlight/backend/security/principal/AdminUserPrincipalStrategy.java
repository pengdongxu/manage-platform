package com.coldlight.backend.security.principal;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.exception.UnauthorizedException;
import com.coldlight.backend.common.request.ClientType;
import com.coldlight.backend.security.AdminUserPrincipal;
import com.coldlight.backend.security.BaseUserPrincipal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 16:53
 * @description 后台管理用户策略实现
 */
@Component
public class AdminUserPrincipalStrategy implements UserPrincipalStrategy {

    @Override
    public boolean supports(UserType userType) {
        return UserType.ADMIN.equals(userType);
    }

    @Override
    public BaseUserPrincipal handle(Claims claims, HttpServletRequest request) {
        // 1. 获取基本信息
        String userId = claims.getSubject();
        String username = claims.get("username", String.class);
        String clientTypeInToken = claims.get("clientType", String.class);
        String clientTypeInHeader = request.getHeader("X-Client-Type");

        // 2. 基础校验：token字段合法性
        if (!ClientType.MANAGER.getValue().equals(clientTypeInHeader) || !clientTypeInToken.equals(clientTypeInHeader)) {
            throw new UnauthorizedException("Client-Type 不一致");
        }

        // 3. 业务数据校验
  /*      SysUser user = userService.getById(userId);
        if (user == null || user.getDeleted() == 1 || user.getStatus() == 0) {
            throw new UnauthorizedException("用户已禁用或不存在");
        }

        // 4. 加载权限角色（可从数据库或缓存中获取）
        List<String> roles = userService.getUserRoles(userId);*/
        List<String> roles = null;

        // 5. 构建 Principal
        return new AdminUserPrincipal(userId, username, null, roles, null, null);
    }
}
