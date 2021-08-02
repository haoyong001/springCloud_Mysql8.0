package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Mr. Hao
 * @date 2021-06-09   16:45
 */
public interface LoadBalanced {
    ServiceInstance instance(List<ServiceInstance> serviceInstance);
}
