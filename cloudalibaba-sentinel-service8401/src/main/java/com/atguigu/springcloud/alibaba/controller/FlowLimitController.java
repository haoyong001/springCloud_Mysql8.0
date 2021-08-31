package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr. Hao
 * @date 2021-08-26   21:57
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "testA==========";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info("***********" + Thread.currentThread().getName() + "\t" + "-----testB");
        return "testB==========";
    }

    @GetMapping("/testC")
    public String testC() {
        log.info("***********异常比例-----testC");
        int age = 10 / 0;
        return "testC异常比例==========";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("***********" + Thread.currentThread().getName() + "\t" + "-----testD");
        return "testD==========";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "blocHotKeyException")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2) {
        return "********testHotKey测试热点数据规则**********";
    }

    public String blocHotKeyException(String p1, String p2, BlockException blockException) {
        return "p1:" + p1 + ",p2:" + p2 + ",热点数据异常！请稍后再试！";
    }

    @GetMapping("/ratelimit/byUrl")
    @SentinelResource(value = "testRate")
    public String testRate(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2) {
        return "********testRate测试流控规则持久化**********";
    }
}
