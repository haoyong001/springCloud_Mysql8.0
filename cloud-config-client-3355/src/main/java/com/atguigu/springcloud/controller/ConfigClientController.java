package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr. Hao
 * @date 2021-08-10   15:19
 */
@RestController
@RefreshScope//手动刷新：如果配置文件修改了，不想重启3355，
// 需要运维人员发送post请求，刷新3355 curl -X POST "http://localhost:3355/actuator/refresh"
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        System.out.println("访问的3355：" + configInfo);
        return port + ":" + configInfo;
    }
}
