package com.vinzor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author 爆山大爷
 * 这里使用了Spymemcached
 */
@SpringBootApplication
public class MemcachedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemcachedApplication.class, args);
	}

}
