package com.vinzor.rabbit.hello;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//可支持对象发送与接收 send（Object） 发送的是 json 接收端用相应对象接收
@Component
public class HelloSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	//向hello队列发
	public void send() {
		String context="hello"+LocalDateTime.now();
		System.out.println("Sender:"+context);
		this.rabbitTemplate.convertAndSend("hello",context);
	}
}
