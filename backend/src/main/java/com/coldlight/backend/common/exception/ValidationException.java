package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:57
 * @description 表单验证失败
 */
public class ValidationException  extends ApiException {
    public static final String CODE = "VALIDATION_FAILED";

    public ValidationException(String message) {
        super(CODE, message);
    }
}
