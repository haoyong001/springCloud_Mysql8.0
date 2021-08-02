package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Mr. Hao
 * @date 2021-06-10   16:48
 */
@SpringBootApplication
@EnableFeignClients//使用feign并开启激活
public class ConsumerFeignOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignOrderMain.class, args);
    }
}
