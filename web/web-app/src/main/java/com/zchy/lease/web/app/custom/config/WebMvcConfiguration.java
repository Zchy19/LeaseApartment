package com.zchy.lease.web.app.custom.config;

import com.zchy.lease.web.app.custom.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: lease
 * @package: com.zchy.lease.web.app.custom.config
 * @className: WebMvcConfiguration
 * @author: ZCH
 * @description:
 * @date: 8/8/2024 10:28 PM
 * @version: 1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/login/**");
    }

}
