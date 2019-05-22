package com.vinzor.rabbit.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="fanout.B")
public class FanoutReceiverB {

	@RabbitHandler
	public void process(String message) {
		System.out.println("ReceiverB:"+message);
	}
}
