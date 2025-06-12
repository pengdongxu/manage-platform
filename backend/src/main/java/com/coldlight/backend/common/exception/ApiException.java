package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:28
 * @description 自定义接口异常基类
 */
public class ApiException extends RuntimeException {

    private final String code;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
