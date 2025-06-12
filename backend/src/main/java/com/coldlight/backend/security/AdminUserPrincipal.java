package com.coldlight.backend.security;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.request.ClientType;

import java.util.List;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 11:52
 * @description 电脑端用户
 */
public class AdminUserPrincipal extends BaseUserPrincipal {

    public AdminUserPrincipal(String userId, String username, String password, List<String> roles, UserType userType, ClientType clientType) {
        super(userId, username, password, roles, userType, clientType);
    }
}
