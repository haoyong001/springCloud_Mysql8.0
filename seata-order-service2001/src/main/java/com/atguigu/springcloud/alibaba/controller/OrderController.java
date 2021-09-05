package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mr. Hao
 * @date 2021-09-02   23:23
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/createOrder")
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public CommonResult createOrder(Order order) {
        orderService.createOrder(order);
        return new CommonResult(200, "订单创建成功！");
    }
}
