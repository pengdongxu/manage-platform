package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:57
 * @description 数据冲突
 * 数据重复、状态冲突
 */
public class ConflictException extends ApiException {
    public static final String CODE = "CONFLICT";

    public ConflictException(String message) {
        super(CODE, message);
    }
}
