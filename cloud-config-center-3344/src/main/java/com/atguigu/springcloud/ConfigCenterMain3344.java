package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Mr. Hao
 * @date 2021-08-02   17:33
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient//手动刷新配置文件：curl -X POST "http://localhost:3344/actuator/bus-refresh"
//刷新指定的客户端：curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"  在eureka的服务名加端口号
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
