package com.atguigu.springcloud.alibaba.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author Mr. Hao
 * @date 2021-09-05   0:15
 */
@Mapper
public interface AccountDao {
    public void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
