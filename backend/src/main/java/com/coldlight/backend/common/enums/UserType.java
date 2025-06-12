package com.coldlight.backend.common.enums;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 11:03
 * @description 用户类型枚举
 */
public enum UserType {
    ADMIN("admin"),
    CUSER("cuser"),
    ;

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String value) {
        for (UserType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid user type: " + value);
    }
}
