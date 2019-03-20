package com.vinzor.customer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.vinzor.entity.Order;

@Component
public class MqCustomer {

	//第一个true 表示持久化
	@RabbitListener(bindings=@QueueBinding(
			value=@Queue(value="order-queue",durable="true"),
			exchange=@Exchange(name="order-exchange",durable="true"),
			key="order.*"
			))
	@RabbitHandler //标识方法
	public void onMessage(@Payload Order order //该注解为消息体
			,@Headers Map<String, Object> headers //消息头
			,Channel channel
			) throws Exception{
		//消费者操作
		System.out.println("-------------------已经收到————————————————————"+order.getMessageId());
		
		Long deliveryTag=(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		//签收
		channel.basicAck(deliveryTag, false); //false 不支持批量签收
		
		
	}
}
