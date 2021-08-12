package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr. Hao
 * @date 2021-08-12   13:54
 * @RestController
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageeListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        String payload = message.getPayload();

        System.out.println("消费者8802------>，接收到消息：" + payload + "\t port:" + serverPort);
    }
}
