package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Mr. Hao
 * @date 2021-08-30   17:08
 */
@RestController
@Slf4j
public class CircleBreakerController {

    /*public static final String SERVIEURL ="http://provider-payment9003-9004";*/

    @Value("${service-url.nacos-user-service}")
    private String SERVIEURL;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/paymentSQL/{id}")
    // @SentinelResource(value = "fallback")//没有配置兜底的异常方法
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")//截获java运行时异常
//    @SentinelResource(value = "fallback",blockHandler = "myBlockHandler")//截获sentinel控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "myBlockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        System.out.println(SERVIEURL + "/paymentSQL/" + id);
        CommonResult<Payment> result = restTemplate.getForObject(SERVIEURL + "/paymentSQL/" + id, CommonResult.class, id);
        System.out.println(result);
        if (5 == id) {
            throw new IllegalArgumentException("非法参数异常********");
        } else if (result.getDate() == null) {
            throw new NullPointerException("该ID没有记录，空指针异常****");
        }
        return result;

    }

    public CommonResult<Payment> myBlockHandler(@PathVariable Long id, BlockException b) {
        Payment payment = new Payment(id, null);
        CommonResult<Payment> result = new CommonResult<>(445, "sentinel控制台配置异常方法，异常信息：" + b.getMessage(), payment);
        return result;
    }

    public CommonResult<Payment> handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        CommonResult<Payment> result = new CommonResult<>(444, "兜底的异常方法，异常信息：" + e.getMessage(), payment);
        return result;
    }

}
