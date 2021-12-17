package com.happy233.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通过配置类注册添加拦截器
 * 该配置类需要实现 WebMvcConfigurer接口 并注入需要配置的拦截器 通过Override addInterceptor方法添加拦截器
 * 并使用addPathPatterns指定拦截路径
 * 可以继续使用excludePathPatterns排除拦截路径(链式编程)
 *
 * @author zy136
 * @date 2021/12/17 17:45
 */
@Configuration
public class SpringInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor).addPathPatterns("/test/**");
    }
}
