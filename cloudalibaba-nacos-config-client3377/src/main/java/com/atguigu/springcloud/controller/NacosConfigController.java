package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr. Hao
 * @date 2021-08-20   10:07
 */
@RestController
@RefreshScope  //支持nacos动态刷新
public class NacosConfigController {
    @Value("${config.info}")
    private String nacosInfo;

    @GetMapping("/config/getinfo")
    public String getNacosInfo() {
        return nacosInfo;
    }
}
