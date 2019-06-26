package com.yang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//启用swagger
@EnableSwagger2
public class Swagger2Config {

	@Bean
	  public Docket createRestApi() {
	    return new Docket(DocumentationType.SWAGGER_2) //
	        .genericModelSubstitutes(DeferredResult.class) //
	        .useDefaultResponseMessages(false) //
	        .forCodeGeneration(true) //
	        .apiInfo(apiInfo()) //
	        .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
	        .select() //
	        .apis(RequestHandlerSelectors.basePackage("com.yang.controller")) //
	        .paths(PathSelectors.any()) //
	        .build(); //
	  }
	  private ApiInfo apiInfo() {
		    return new ApiInfoBuilder() //
		        .title("springboot利用swagger构建api文档的小例子") //
		        .description("这就是一个例子") //
		        .termsOfServiceUrl("https://github.com/yang330773545/SpringbootTest") //
		        .version("1.0") //
		        .build();
	  }

}
