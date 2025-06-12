package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:57
 * @description 系统内部错误
 */
public class SystemException  extends ApiException {
    public static final String CODE = "SYSTEM_ERROR";

    public SystemException(String message) {
        super(CODE, message);
    }
}
