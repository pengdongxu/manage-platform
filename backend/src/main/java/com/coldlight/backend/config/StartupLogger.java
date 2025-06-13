package com.coldlight.backend.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/12 17:22
 * @description 启动时打印启动信息
 */
@Component
public class StartupLogger implements ApplicationRunner {
    private final Environment env;

    public StartupLogger(Environment env) {
        this.env = env;
    }

    @Override
    public void run(ApplicationArguments args) {
        String activeProfile = env.getProperty("spring.profiles.active", "default");

        if ("dev".equalsIgnoreCase(activeProfile)) {
            try {
                String port = env.getProperty("server.port", "8080");
                String contextPath = env.getProperty("server.servlet.context-path", "");
                if (contextPath == null) contextPath = "";

                String localhost = "http://localhost:" + port + contextPath;
                String ip = InetAddress.getLocalHost().getHostAddress();
                String lanUrl = "http://" + ip + ":" + port + contextPath;

                System.out.println("\n================ 🚀 服务启动成功 ================\n");
                System.out.println("本地访问地址: \t" + localhost);
                System.out.println("局域网访问地址: \t" + lanUrl);
                System.out.println("健康检查接口: \t" + localhost + "/actuator/health");
                System.out.println("\n================================================\n");

            } catch (UnknownHostException e) {
                System.err.println("获取主机地址失败: " + e.getMessage());
            }
        }
    }
}
