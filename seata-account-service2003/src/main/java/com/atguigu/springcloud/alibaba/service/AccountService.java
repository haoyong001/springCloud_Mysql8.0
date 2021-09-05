package com.atguigu.springcloud.alibaba.service;


import com.atguigu.springcloud.alibaba.domain.CommonResult;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author Mr. Hao
 * @date 2021-09-05   0:30
 */
public interface AccountService {
    /**
     * @param userId
     * @param money
     * @return
     */
    public CommonResult decrease(Long userId, BigDecimal money);
}
