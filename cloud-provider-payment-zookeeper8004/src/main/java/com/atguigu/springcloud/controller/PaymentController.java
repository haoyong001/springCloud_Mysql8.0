package com.atguigu.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Mr. Hao
 * @date 2021-06-07   23:41
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/zk")
    public String paymentZk() {
        log.info("spring cloud with zookeeper : serverPort:" + serverPort + "**" + UUID.randomUUID().toString());
        return "spring cloud with zookeeper : serverPort:" + serverPort + "**" + UUID.randomUUID().toString();
    }
}
