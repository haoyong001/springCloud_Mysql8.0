package com.atguigu.springcloud.alibaba.service;

/**
 * @author Mr. Hao
 * @date 2021-09-04   23:50
 */
public interface StorageService {
    //扣减库存
    void decrease(Long productId, Integer count);
}
