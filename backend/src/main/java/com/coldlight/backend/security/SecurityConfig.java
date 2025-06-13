package com.coldlight.backend.security;

import com.coldlight.backend.common.jwt.JwtAuthenticationFilter;
import com.coldlight.backend.common.jwt.TokenServiceImpl;
import com.coldlight.backend.config.SecurityProperties;
import com.coldlight.backend.security.handler.JwtAccessDeniedHandler;
import com.coldlight.backend.security.handler.JwtAuthenticationEntryPoint;
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
    private final SecurityProperties properties;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenServiceImpl tokenService,
            List<UserPrincipalStrategy> principalStrategies,
            SecurityProperties properties,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenService = tokenService;
        this.principalStrategies = principalStrategies;
        this.properties = properties;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(properties.getGlobalWhiteList().toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenService, principalStrategies);
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
