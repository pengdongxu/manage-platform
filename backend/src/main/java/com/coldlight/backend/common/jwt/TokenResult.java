package com.coldlight.backend.common.jwt;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 16:55
 * @description 令牌结果
 */
@Getter
public class TokenResult implements Serializable {

    //  accessToken
    private String accessToken;

    //  refreshToken
    private String refreshTokenId;

    // accessToken 过期秒数
    private long expiresIn;

    public TokenResult(String accessToken, String refreshTokenId, long expiresIn) {
        this.accessToken = accessToken;
        this.refreshTokenId = refreshTokenId;
        this.expiresIn = expiresIn;
    }
}
