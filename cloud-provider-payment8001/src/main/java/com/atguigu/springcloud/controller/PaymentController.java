package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.atguigu.springcloud.util.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    @Resource
    private DiscoveryClient discoveryClient;
    public static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
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

    @GetMapping(value = "/payment/discoveryClient")
    public Object discoveryClient() {
        List<String> services = discoveryClient.getServices();
        for (String ele : services) {
            log.info("************ ele:" + ele);
            List<ServiceInstance> instances = discoveryClient.getInstances(ele);
            for (ServiceInstance instance : instances) {
                log.info("******************* instance:host:" + instance.getHost() + ",port:" + instance.getPort() + ",uri:" + instance.getUri());
            }
        }
        return discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPort() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String getZipkin() {
        return serverPort + ":hi,i am zipkin welcome to my home";
    }

    @Resource
    private IdGeneratorSnowflake snowflake;

    @GetMapping("/payment/getsnowflake")
    public String getSnowFlake(HttpServletResponse response) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            threadPool.submit(() -> {
                long snowflakeId = snowflake.snowflakeId();
                System.out.println(snowflake.snowflakeId());
            });
        }
        threadPool.shutdown();
        String s1 = "获取雪花算法全局唯一ID成功！";
        String s2 = "";
        try {
            s2 = new String(s1.getBytes("UTF-8"), "ISO-8859-1");
            System.out.println(s2);
            String s11 = new String("测试".getBytes("GBK"), "iso-8859-1");
            System.out.println(s11);
            byte[] bytes = s2.getBytes("iso-8859-1");
            System.out.println(bytes);
            String s12 = new String(bytes, "UTF-8");
            System.out.println("s12:" + s12);
//            response.setContentType ("text/html;charset=UTF-8");
//            response.getWriter().write("获取雪花算法全局唯一ID成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s2;
    }
}
