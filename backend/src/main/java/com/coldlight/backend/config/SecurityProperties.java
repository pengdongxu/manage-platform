package com.coldlight.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/13 11:10
 * @description  配置类
 */
@ConfigurationProperties(prefix = "coldlight.security")
public class SecurityProperties {

    //白名单
    private List<String> globalWhiteList;

    public List<String> getGlobalWhiteList() {
        return globalWhiteList;
    }

    public void setGlobalWhiteList(List<String> globalWhiteList) {
        this.globalWhiteList = globalWhiteList;
    }
}
