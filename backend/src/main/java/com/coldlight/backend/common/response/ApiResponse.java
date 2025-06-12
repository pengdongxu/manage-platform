package com.coldlight.backend.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 15:09
 * @description 统一 API 响应格式
 */
public class ApiResponse<T> {

    private String code;
    private String msg;
    private T data;
    private LocalDateTime timestamp;


    public ApiResponse(String code, String msg, T data, LocalDateTime timestamp) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", "操作成功", data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null, LocalDateTime.now());
    }
}
