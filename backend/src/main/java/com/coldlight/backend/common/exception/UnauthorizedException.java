package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:33
 * @description 未认证异常（401）
 * Token 失效、登录失败
 */
public class UnauthorizedException extends ApiException {

    public static final String CODE = "UNAUTHORIZED";

    public UnauthorizedException() {
        super(CODE, "未登录或令牌已过期");
    }

    public UnauthorizedException(String message) {
        super(CODE, message);
    }
}
