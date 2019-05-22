package com.vinzor.rabbit.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void sendA() {
		String message="messageA";
		this.rabbitTemplate.convertAndSend("fanoutExchange", "fanout.A", message);
	}
	public void sendB() {
		String message="messageB";
		this.rabbitTemplate.convertAndSend("fanoutExchange", "fanout.B", message);
	}
	public void sendC() {
		String message="messageC";
		this.rabbitTemplate.convertAndSend("fanoutExchange", "fanout.C", message);
	}
}
