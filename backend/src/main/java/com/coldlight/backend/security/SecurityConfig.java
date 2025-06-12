package com.coldlight.backend.security;

import com.coldlight.backend.common.jwt.TokenServiceImpl;
import com.coldlight.backend.config.JwtAuthenticationFilter;
import com.coldlight.backend.security.principal.UserPrincipalStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/6 15:45
 * @description 配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenServiceImpl tokenService;
    private final List<UserPrincipalStrategy> principalStrategies;

    public SecurityConfig(TokenServiceImpl tokenService, List<UserPrincipalStrategy> principalStrategies) {
        this.tokenService = tokenService;
        this.principalStrategies = principalStrategies;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/public/**", "/actuator/health")
                        .permitAll()
                        .anyRequest().authenticated() // 其他接口必须登录后访问
                )
                .formLogin(AbstractHttpConfigurer::disable) // 禁用表单登录
                .logout(AbstractHttpConfigurer::disable); // 禁用默认登出

        http.addFilterBefore(new JwtAuthenticationFilter(tokenService, principalStrategies), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    /**
     * 密码加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * JWT项目不使用默认的UserDetailsService
     * 这个是去掉启动项目打印 Spring Security 自动生成的默认用户
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new UsernameNotFoundException("This project does not use default login");
        };
    }
}
