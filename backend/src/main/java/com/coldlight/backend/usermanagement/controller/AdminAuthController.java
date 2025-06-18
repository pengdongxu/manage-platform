package com.coldlight.backend.usermanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/13 14:52
 * @description 系统管理员权限认证
 */
@RestController
public class AdminAuthController {

    @PostMapping("/login")
    public String adminAuth() {
        return "系统管理员权限认证成功";
    }

    @PostMapping("/logout")
    public String adminLogout() {
        return "系统管理员权限注销成功";
    }
}
