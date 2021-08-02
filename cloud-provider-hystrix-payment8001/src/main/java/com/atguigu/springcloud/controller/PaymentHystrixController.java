package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mr. Hao
 * @date 2021-06-21   17:27
 */
@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentinfo_ok(id);
        log.info("result ： ok:" + result);
        return result;
    }

    ;

    @GetMapping("/payment/hystrix/timeout/{id}&{time}")
    public String paymentinfo_timeout(@PathVariable("id") Integer id, @PathVariable("time") Integer time) {
        String result = paymentHystrixService.paymentinfo_timeout(id, time);
        log.info("result ： timeout:" + result);
        return result;
    }

    ;

    //服务熔断
    @GetMapping("/payment/circuit/breaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info("result ： CircuitBreaker:" + result);
        return result;
    }

    ;
}
