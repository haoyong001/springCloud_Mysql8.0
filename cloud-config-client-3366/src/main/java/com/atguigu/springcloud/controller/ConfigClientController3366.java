package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr. Hao
 * @date 2021-08-11   9:54
 */
@RestController
@RefreshScope //要想自动刷新配置文件，必须用次注解
public class ConfigClientController3366 {
    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        System.out.println("访问的3366：" + configInfo);
        return port + ":" + configInfo;
    }
}
