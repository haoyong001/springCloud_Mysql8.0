package com.atguigu.springcloud.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Mr. Hao
 * @date 2021-06-24   14:48
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentinfo_ok(Integer id) {
        return "PaymentFallbackService:paymentinfo_ok------异常配置返回 " + id;
    }

    @Override
    public String paymentinfo_timeout(Integer id, Integer time) {
        return "PaymentFallbackService:paymentinfo_timeout------异常配置 " + id + "超时时间：" + time;
    }
}
