package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Mr. Hao
 * @date 2021-06-01   0:18
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    public int delete(@Param("id") Long id);

    int delByTime(@Param("payment") String payment, @Param("time") Date time);
}
