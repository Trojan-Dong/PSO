package com.trojan.test;

import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class TestSender {


    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());


    private KafkaTemplate<String, String> kafkaTemplate;


    //发送消息方法
    public void send() {
        TestMessage message = new TestMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        kafkaTemplate.send("zhisheng", JSON.toJSONString(message));
    }
}
