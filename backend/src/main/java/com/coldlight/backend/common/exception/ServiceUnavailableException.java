package com.coldlight.backend.common.exception;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:57
 * @description 第三方服务不可用
 * 第三方服务宕机、限流降级
 */
public class ServiceUnavailableException extends ApiException {
    public static final String CODE = "SERVICE_UNAVAILABLE";

    public ServiceUnavailableException(String message) {
        super(CODE, message);
    }
}