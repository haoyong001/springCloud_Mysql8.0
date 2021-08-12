package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Mr. Hao
 * @date 2021-08-11   23:54
 */
@EnableBinding(Source.class)//定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    @Qualifier("output")
    private MessageChannel outPut;  //消息发送管道

    @Override
    public String send() {
        String value = UUID.randomUUID().toString();
        outPut.send(MessageBuilder.withPayload(value).build());
        System.out.println("********消息生产者8801：" + value);
        return value;
    }
}
