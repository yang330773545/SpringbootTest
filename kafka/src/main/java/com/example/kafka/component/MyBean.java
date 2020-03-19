package com.example.kafka.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    private final KafkaTemplate kafkaTemplate;
    // kafkaTemplate 是自动配置的 可使用其send方法发送消息
    @Autowired
    public MyBean(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message){
        // topic 分区 时间 key data key和data都可为空 分区可使一个topic负载均衡到多个服务器 一个数据包含 时间 k v
        kafkaTemplate.send("someTopic",1,1l,"key","value");
    }
    // 接收信息 监听topic为someTopic
    @KafkaListener(topics = "someTopic")
    public void processMessage(String content) {
        // ...
    }
}
