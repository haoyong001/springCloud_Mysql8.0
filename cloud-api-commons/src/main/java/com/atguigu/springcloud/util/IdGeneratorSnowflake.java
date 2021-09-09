package com.atguigu.springcloud.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.LinkedHashSet;

/**
 * @author Mr. Hao
 * @date 2021-09-07   22:01
 */
@Component
public class IdGeneratorSnowflake {
    private long workerId = 0;//取值范围0--31
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
    public static final Logger LOGGER = LoggerFactory.getLogger(IdGeneratorSnowflake.class);

    @PostConstruct          //表示程序加载完成以后就初始化
    private void init() {
        try {
            String localhost = NetUtil.getLocalhostStr();
            InetAddress localhost1 = NetUtil.getLocalhost();
            String localhost2 = NetUtil.getIpByHost("localhost");
            LinkedHashSet<String> strings = NetUtil.localIpv4s();
            workerId = NetUtil.ipv4ToLong(localhost);
            LOGGER.info("测试中文日志乱码");
            System.out.println("strings雪花算法:" + strings.toString() + "localhost:" + localhost + ",workerId:" + workerId);
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
            System.out.println("异常workerId:" + workerId);
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowflake().snowflakeId());
    }

}
