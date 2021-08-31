package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Mr. Hao
 * @date 2021-08-30   15:13
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> map = new HashMap<>();

    static {
        map.put(1L, new Payment(1L, "11111111111111111111"));
        map.put(2L, new Payment(2L, "22222222222222222222"));
        map.put(3L, new Payment(3L, "33333333333333333333"));
        map.put(4L, new Payment(4L, "44444444444444444444"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        System.out.println(id);
        Payment payment = map.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "from mysql,serverport:" + serverPort, payment);
        return result;
    }

}
