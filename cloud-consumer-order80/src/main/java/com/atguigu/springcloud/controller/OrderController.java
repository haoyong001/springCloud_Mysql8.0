package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author Mr. Hao
 * @date 2021-06-01   23:01
 */
@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";  //单机版
    //集群
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    //引入自定义的负载均衡算法
    @Resource
    public LoadBalanced loadBalanced;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("***********consumer新增" + payment);
        String serial = payment.getSerial();

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        log.info("***********consumer查询");

        //使用restTemplate的负载均衡
        //return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        //使用自定义的算法的loadBalanced负载均衡
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance instance = loadBalanced.instance(instances);
        URI uri = instance.getUri();
        System.out.println("uri:=====" + uri);
        return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        log.info("********consumer删除");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/delete/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance instance = loadBalanced.instance(instances);
        URI uri = instance.getUri();
        String port = restTemplate.getForObject(uri + "/payment/lb", String.class);
        System.out.println(port);
        return "********访问的是:" + port;
    }

    /*开启负载均衡,服务提供者集群配置的时候,消费者以服务名称寻址,就需要这个注解@LoadBalanced在restTemplate,
     赋予RestTemplate负载均衡能力,如果用自定义的loadBalanced负载均衡，就需要关闭restTemplate这个负载均衡
     */
    @GetMapping("/consumer/payment/zipkin")
    public String getZipkin() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance instance = loadBalanced.instance(instances);
        URI uri = instance.getUri();
        System.out.println("uri:=====" + uri);
        return restTemplate.getForObject(uri + "/payment/zipkin", String.class);
    }
}
