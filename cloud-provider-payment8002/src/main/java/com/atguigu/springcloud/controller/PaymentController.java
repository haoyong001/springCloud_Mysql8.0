package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Slf4j日志
 *
 * @author Mr. Hao
 * @date 2021-06-01   0:29
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*********插入结果: " + result + ":" + payment);
        if (result > 0) {
            return new CommonResult(200, "调用服务:" + serverPort + ",数据库插入成功!", result);
        } else {
            return new CommonResult(444, "调用服务:" + serverPort + ",数据库插入失败!", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*********查询结果: " + payment);
        if (payment != null) {
            return new CommonResult(200, "调用服务:" + serverPort + ",查询成功哈哈!", payment);
        } else {
            return new CommonResult(444, "调用服务:" + serverPort + ",数据库没有对应记录,查询失败!", null);
        }
    }

    @GetMapping(value = "/payment/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int result = paymentService.delete(id);
        log.info("*********删除结果: " + result);
        if (result > 0) {
            return new CommonResult(200, "调用服务:" + serverPort + ",删除成功!", result);
        } else {
            return new CommonResult(444, "调用服务:" + serverPort + ",数据库没有对应记录,删除失败!", null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPort() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
