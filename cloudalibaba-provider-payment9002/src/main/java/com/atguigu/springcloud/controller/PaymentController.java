package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr. Hao
 * @date 2021-08-18   0:15
 */
@RestController
@Slf4j
@RefreshScope  //支持nacos动态刷新
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "访问nacos，端口号：" + serverPort + ",id=" + id;
    }

    @GetMapping("payment/nacos/getInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
