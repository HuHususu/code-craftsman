package com.husu.common.config;

import com.husu.common.interceptor.LoggerContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/25 10:52
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Logger
        registry.addInterceptor(new LoggerContextInterceptor()).addPathPatterns("/**");
    }
}

