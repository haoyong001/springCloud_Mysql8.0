package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Mr. Hao
 * @date 2021-06-21   15:57
 */
public interface PaymentHystrixService {

    public String paymentinfo_ok(Integer id);

    public String paymentinfo_timeout(Integer id, Integer time);

    public String paymentCircuitBreaker(Integer id);
}
