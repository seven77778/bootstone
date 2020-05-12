package com.lsh.demo.bootstone.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by lsh on 2020-05-12.
 *
 * springboot配置类，代替spring mvc的 <mvc:interceptors> 配置
 *
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }
}
