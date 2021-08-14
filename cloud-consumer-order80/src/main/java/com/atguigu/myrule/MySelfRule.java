package com.atguigu.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr. Hao
 * @date 2021-06-09   9:34
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule getRule() {
        IRule randomRule = null;
        //随机规则
        randomRule = new RandomRule();
        //轮询
        // randomRule = new RoundRobinRule();
        //重试,先按照轮询策略获取服务,如果获取服务失败,则在指定时间内重试
//        randomRule = new RetryRule();
        //对轮询的扩展,响应速度越快的实例选择权重越大越容易被选中
//        randomRule = new WeightedResponseTimeRule();
        //会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务,然后选择一个并发量最小的服务
        // randomRule = new BestAvailableRule();
        //先过滤掉故障实例,再选择并发较小的实例
//        randomRule = new AvailabilityFilteringRule();
        //默认策略,复合判断server所在区域的性能和server的可用性选择服务器
//        randomRule = new ZoneAvoidanceRule();
        return randomRule;
    }
}
