package com.vinzor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vinzor.mysql.mapper")
@SpringBootApplication
public class NoPaperTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoPaperTestApplication.class, args);
	}

}
