package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr. Hao
 * @date 2021-06-22   11:58
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentGlobalFailback")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    //配置了制定的fallback就用制定的，没有就用全局的服务降级配置
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    //  @HystrixCommand
    public String paymentinfo_ok(@PathVariable("id") Integer id) {
        //  int i = 10/0;
        String result = paymentHystrixService.paymentinfo_ok(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
   /* @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
            @HystrixProperty(name=  "execution.isolation.thread.timeoutInMilliseconds" ,value="3000")
    })*/
    public String paymentinfo_timeout(@PathVariable("id") Integer id) {
        int timeNum = 1;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = paymentHystrixService.paymentinfo_timeout(id, timeNum);
        return result;
    }

    /**
     * 超时提醒
     */
    public String paymentTimeoutHandler(Integer id) {
        return id + ":order资源正忙，稍后再试！！";
    }

    /**
     * 全局异常提示
     */
    public String paymentGlobalFailback() {
        return "全局默认的异常返回提示，资源忙，稍后再试！";
    }
}
