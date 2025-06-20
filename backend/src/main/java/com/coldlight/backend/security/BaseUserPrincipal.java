package com.coldlight.backend.security;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.base.request.ClientType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 11:19
 * @description 基础用户信息
 */
public abstract class BaseUserPrincipal implements UserDetails {
    private String userId;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    //用户类型
    private UserType userType;
    //终端类型
    private ClientType clientType;

    public BaseUserPrincipal(String userId, String username, String password, List<String> roles, UserType userType, ClientType clientType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.userType = userType;
        this.clientType = clientType;
    }

    // 以下是 UserDetails 接口需要实现的几个方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public String getUserId() { return userId; }
}
