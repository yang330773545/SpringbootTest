package com.vinzor.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinzor.entity.Order;

@Component
public class MqProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	public void sendOrder(Order order) throws Exception {
		CorrelationData correlationData=new CorrelationData();
		correlationData.setId(order.getMessageId());
		rabbitTemplate.convertAndSend("order-exchange",
				"order.abcd",
				order,
				correlationData);//唯一id
	}
}
