package com.vinzor.conf;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig {

	@Bean
	public ErrorPageRegistrar errorPageRegistrar(){
	    return new ErrorpageRegistrar();
	}
	public class ErrorpageRegistrar implements ErrorPageRegistrar{

		@Override
		public void registerErrorPages(ErrorPageRegistry registry) {
			// TODO Auto-generated method stub
			ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
	        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
	        registry.addErrorPages(page404, page500);
		}
		
	}
}
