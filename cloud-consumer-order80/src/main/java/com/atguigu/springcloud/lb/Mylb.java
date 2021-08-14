package com.atguigu.springcloud.lb;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr. Hao
 * @date 2021-06-09   17:26@Component
 */
@Component
public class Mylb implements LoadBalanced {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (
            //自旋锁
                !this.atomicInteger.compareAndSet(current, next)
        );
        System.out.println("第" + next + "次访问");
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstance) {
        //取余
        int index = getAndIncrement() % serviceInstance.size();
        ServiceInstance instance = serviceInstance.get(index);
        return instance;
    }
}
