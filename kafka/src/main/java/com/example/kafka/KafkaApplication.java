package com.example.kafka;

import com.example.kafka.service.MyBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @RestController
    public class TestController{
        @Autowired
        private MyBeanService myBeanService;

        @GetMapping(value = "/topic/{message} ")
        public String test(@PathVariable String message){
            myBeanService.sendMessage(message);
            return message;
        }
    }
}
