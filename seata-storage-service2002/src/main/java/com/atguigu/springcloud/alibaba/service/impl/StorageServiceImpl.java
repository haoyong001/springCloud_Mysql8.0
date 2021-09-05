package com.atguigu.springcloud.alibaba.service.impl;

import cn.hutool.log.LogFactory;
import com.atguigu.springcloud.alibaba.dao.StorageDao;
import com.atguigu.springcloud.alibaba.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Mr. Hao
 * @date 2021-09-04   23:53
 */
@Service
public class StorageServiceImpl implements StorageService {
    public static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("--------->storage-service中扣减库存开始");
        storageDao.decrease(productId, count);
        LOGGER.info("--------->storage-service中扣减库存结束");
    }
}
