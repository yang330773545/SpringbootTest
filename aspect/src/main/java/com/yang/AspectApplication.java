package com.yang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.annotation.LogAnnotation;

@SpringBootApplication
@RestController
public class AspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectApplication.class, args);
	}

	@GetMapping("/aspect")
	@LogAnnotation()
	public String look() {
		return "来看狗子";
	}
}
