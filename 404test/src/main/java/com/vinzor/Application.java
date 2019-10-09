package com.vinzor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//404 500 等可以直接在resources目录下的  /public/error  下而不用实现 ErrorPageRegistrar和ErrorController

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
