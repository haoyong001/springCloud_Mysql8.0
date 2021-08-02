package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Mr. Hao
 * @date 2021-06-08   17:33
 */
@RestController
@Slf4j
public class PaymentConsulController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentConsul() {
        log.info("spring cloud with consul : serverPort:" + serverPort + "**" + UUID.randomUUID().toString());
        return "spring cloud with consul : serverPort:" + serverPort + "**" + UUID.randomUUID().toString();
    }
}
