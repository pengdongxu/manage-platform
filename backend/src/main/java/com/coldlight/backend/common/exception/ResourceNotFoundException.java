package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:57
 * @description 资源不存在（查询不到数据）
 */
public class ResourceNotFoundException extends ApiException {
    public static final String CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException(String message) {
        super(CODE, message);
    }
}