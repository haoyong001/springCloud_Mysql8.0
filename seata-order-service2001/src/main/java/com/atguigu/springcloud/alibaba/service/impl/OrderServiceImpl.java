package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Mr. Hao
 * @date 2021-09-01   23:35
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    //订单
    @Resource
    private OrderDao orderDao;
    //账户余额
    @Resource
    private AccountService accountService;
    //库存
    @Resource
    private StorageService storageService;

    @Override
    public void createOrder(Order order) {
        log.info("----------->开始创建订单--------");
        orderDao.createOrder(order);
        log.info("----------->订单创建完毕--------");
        log.info("----------->订单微服务开始调用库存微服务，做扣减库存操作--------");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----------->订单微服务开始调用库存微服务，库存做扣减end--------");
        log.info("----------->订单微服务开始调用扣减账户微服务，做扣减金额操作--------");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("账户金额做扣减end***********************");
        log.info("----------->开始修改订单状态--------");
        orderDao.updateOrder(order.getUserId(), 0);
        log.info("----------->修改订单状态结束--------");
    }

    @Override
    public void updateOrder(Long userId, Integer status) {
        orderDao.updateOrder(userId, status);
    }
}
