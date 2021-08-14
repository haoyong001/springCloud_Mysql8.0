package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mr. Hao
 * @date 2021-06-01   23:05
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced //开启负载均衡,服务提供者集群配置的时候,消费者以服务名称寻址,就需要这个注解,
    // 赋予RestTemplate负载均衡能力,如果用自定义的loadbalaced，就需要关闭这个负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
