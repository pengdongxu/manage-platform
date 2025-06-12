package com.coldlight.backend.security;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.request.ClientType;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 11:51
 * @description 小程序用户
 */
public class MiniAppUserPrincipal extends BaseUserPrincipal {

    private final String openid;

    public MiniAppUserPrincipal(String userId, String username, String password,
                                List<String> role, ClientType clientType, UserType userType,
                                String openid) {
        super(userId, username, password, role , userType, clientType);
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
}