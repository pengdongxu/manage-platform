package com.coldlight.backend.common.jwt;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.request.ClientType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 17:08
 * @description
 */
@Getter
@AllArgsConstructor
public class AuthPrincipal implements Serializable {
    @Serial
    private static final long serialVersionUID = -1398299059343917950L;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户类型
     */
    private UserType userType;

    /**
     * 终端类型
     */
    private ClientType clientType;



}
