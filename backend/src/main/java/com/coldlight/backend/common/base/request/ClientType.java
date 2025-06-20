package com.coldlight.backend.common.base.request;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 16:32
 * @description  客户端类型枚举
 */
public enum ClientType {

    MANAGER("manager"),
    MINIAPP("miniapp"),
    API("api");

    private final String value;

    ClientType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClientType fromValue(String value) {
        for (ClientType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid client type: " + value);
    }

}
