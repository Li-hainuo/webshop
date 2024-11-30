package com.lihainuo.admin.config;

import com.lihainuo.jwt.config.JwtAuthenticationSecurityConfig;
import com.lihainuo.jwt.filter.TokenAuthenticationFilter;
import com.lihainuo.jwt.handler.RestAccessDeniedHandler;
import com.lihainuo.jwt.handler.RestAuthenticationEntryPoint;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lihainuo
 * @description: Spring Security 配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Resource
    private RestAccessDeniedHandler restAccessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // 禁用 csrf
                .formLogin(AbstractHttpConfigurer::disable) // 禁用表单登录
                .apply(jwtAuthenticationSecurityConfig) // 设置用户登录认证相关配置
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL 资源
                        .anyRequest().permitAll() // 其他都需要放行，无需认证
                );
        // 处理登录成功后访问受保护的资源，但是权限不够的情况
        http.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler);
        // 处理用户未登录访问受保护的资源的情况
        http.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint);
        // 前后端分离，无需创建会话
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        // 将 Token 校验过滤器添加到用户认证过滤器之前
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
}
