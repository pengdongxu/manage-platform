package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:34
 * @description 请求参数错误（400）
 * 参数缺失、格式错误等
 */
public class BadRequestException extends ApiException {

    public static final String CODE = "BAD_REQUEST";

    public BadRequestException() {
        super(CODE, "Bad Request");
    }
    public BadRequestException(String message) {
        super(CODE, message);
    }
}
