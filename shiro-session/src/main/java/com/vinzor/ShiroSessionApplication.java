package com.vinzor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务
@EnableScheduling
@MapperScan("com.vinzor.mysql.dao")
 class ShiroSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroSessionApplication.class, args);
	}

}
