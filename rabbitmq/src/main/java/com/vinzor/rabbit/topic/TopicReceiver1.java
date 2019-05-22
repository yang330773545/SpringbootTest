package com.vinzor.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="topic.message")
public class TopicReceiver1 {

	@RabbitHandler
	public void process(String message) {
		System.out.println("Receiver1:"+message);
	}
}
