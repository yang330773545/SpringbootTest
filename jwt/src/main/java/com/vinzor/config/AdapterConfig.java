package com.vinzor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vinzor.interceptor.MyInterceptor;

@Configuration
public class AdapterConfig implements WebMvcConfigurer {

	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(new MyInterceptor())
	     .excludePathPatterns("/login")
	     ;
	 }
}
