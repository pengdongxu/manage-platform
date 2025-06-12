package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:33
 * @description 无权限访问（403）
 * 用户已登录但无访问权限
 */
public class ForbiddenException  extends ApiException {

    public static final String CODE = "FORBIDDEN";

    public ForbiddenException() {
        super(CODE, "没有权限访问该资源");
    }

    public ForbiddenException(String message) {
        super(CODE, message);
    }
}
