package com.vinzor.initialization;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//SpringApplication.run方法执行前执行用于初始化
@Component
@Order(2)
public class RunnerIntialization1 implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("爆山大爷啊！");
	}

}
