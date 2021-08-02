package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr. Hao
 * @date 2021-06-01   0:23
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    public int delete(@Param("id") Long id);
}
