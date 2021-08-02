package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr. Hao
 * @date 2021-06-21   16:04
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentinfo_ok(Integer id) {
        String mes = "线程池： " + Thread.currentThread().getName() + " paymentinfo_ok,id: " + id + "\t" + "哈哈！！";
        return mes;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentinfo_timeout(Integer id, Integer time) {
        // int a = 10/0;
        int timeNum = 4;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String mes = "线程池： " + Thread.currentThread().getName() + " paymentinfo_timeout,id: " + id + "\t" + "哈哈！！耗时" + time + "秒钟！";
        return mes;
    }

    /**
     * 超时提醒
     */
    public String paymentTimeoutHandler(Integer id, Integer time) {
        return id + ":payment资源正忙，稍后再试！！呜呜。。超时" + time;
    }

    /*服务熔断*/
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                    //是否开启断踏器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),       //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")     //失败率达到多少后跳闸
    })//此配置的含义：在时间窗口期10000毫秒内，请求次数10次中，如果超过60%失败，则触发熔断（默认5秒内10次失败--20*50%）
    public String paymentCircuitBreaker(@PathVariable Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能是负数" + id);
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功！流水号：" + uuid;
    }

    //熔断返回值
    public String paymentCircuitBreaker_fallback(@PathVariable Integer id) {
        return "id不能为负数，请更正后再试！id=" + id;
    }
}
