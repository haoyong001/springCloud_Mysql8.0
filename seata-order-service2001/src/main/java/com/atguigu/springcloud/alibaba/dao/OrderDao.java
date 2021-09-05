package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr. Hao
 * @date 2021-09-01   22:58
 */
@Mapper
public interface OrderDao {
    //新建订单
    void createOrder(Order order);

    //修改订单状态
    void updateOrder(@Param("userId") Long userId, @Param("status") Integer status);
}
