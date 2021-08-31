package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.IPaymentService;
import org.springframework.stereotype.Component;

/**
 * @author Mr. Hao
 * @date 2021-08-31   20:44
 */
@Component
public class PaymentFallbackService implements IPaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，-----PaymentFallbackService", new Payment(id, null));
    }
}
