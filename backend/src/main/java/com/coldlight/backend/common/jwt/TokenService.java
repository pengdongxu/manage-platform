package com.coldlight.backend.common.jwt;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.request.ClientType;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 16:54
 * @description 令牌服务
 */
public interface TokenService {

    TokenResult generateToken(Long userId, UserType userType, ClientType clientType);

    TokenResult refreshToken(String refreshTokenId, UserType userType, ClientType clientType);

    void invalidateToken(String refreshTokenId);
}
