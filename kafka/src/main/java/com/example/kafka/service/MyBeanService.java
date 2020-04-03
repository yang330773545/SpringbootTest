package com.example.kafka.service;

import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class MyBeanService {
    Logger logger = LoggerFactory.getLogger(MyBeanService.class);

    // kafkaTemplate 是自动配置的 可使用其send方法发送消息
    private final KafkaTemplate kafkaTemplate;
    //private KStream kStream;

    @Autowired
    public MyBeanService(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
        //this.kStream = kStream;
    }
    public void sendMessage(String message){
        // topic 分区 时间 key data key和data都可为空 分区可使一个topic负载均衡到多个服务器 一个数据包含 时间 k v
        ListenableFuture<SendResult<String, Object>> future =kafkaTemplate.send("someTopic",1,1l,"key",message);
        // callback回调用来查看是否发送成功
        future.addCallback(
                result -> logger.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
                ex -> logger.error("生产者发送消失败，原因：{}", ex.getMessage()));
        //kStream.to(message);
    }
    // 接收信息 监听topic为someTopic
    @KafkaListener(topics = "someTopic")
    public void processMessage(String content) { //ConsumerRecord<String, String> objectConsumerRecord
        // ...
        logger.info("消费者消费{}的消息 -> {}", "someTopic", content);
    }
}
