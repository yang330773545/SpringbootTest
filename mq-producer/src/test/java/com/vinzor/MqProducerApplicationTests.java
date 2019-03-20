package com.vinzor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinzor.entity.Order;
import com.vinzor.producer.MqProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqProducerApplicationTests {
    @Autowired
    MqProducer mqPorducer;
	
	@Test
	public void contextLoads() {
	}
	@Test
	public void sendOrder() throws Exception {
		Order order=new Order();
		order.setId(1);
		order.setName("name");
		order.setMessageId(LocalDateTime.now()+UUID.randomUUID().toString());
	    mqPorducer.sendOrder(order);
	}

}

