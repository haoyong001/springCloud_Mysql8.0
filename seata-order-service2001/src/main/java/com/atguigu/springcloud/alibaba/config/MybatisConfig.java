package com.atguigu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr. Hao
 * @date 2021-09-02   23:34
 */
@Configuration
@MapperScan("com.atguigu.springcloud.alibaba.dao")
public class MybatisConfig {
}
