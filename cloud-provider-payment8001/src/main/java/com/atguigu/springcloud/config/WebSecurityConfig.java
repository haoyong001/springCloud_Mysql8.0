package com.atguigu.springcloud.config;

import com.atguigu.springcloud.interceptor.MyInterCeptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Mr. Hao
 * @date 2021-08-16   23:21
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {

    @Bean
    public MyInterCeptor getSecurityInterceptor() {
        return new MyInterCeptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/payment/feign/timeout");
       /* addInterceptor.excludePathPatterns("/403");
        addInterceptor.excludePathPatterns("/toLogin");
        addInterceptor.excludePathPatterns("/login**");*/

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
