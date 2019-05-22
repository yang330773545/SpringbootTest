package com.vinzor.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

	//Direct Exchange默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列
	//多对多使用时： 接收端会均匀接收到消息
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}
}
