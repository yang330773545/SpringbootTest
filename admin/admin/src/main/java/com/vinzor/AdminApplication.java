package com.vinzor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 
 * @author 50000008
 *
 *  使用的是单个 Spring Boot 应用，就需要在每一个被监控的应用中配置 Admin Server 的地址信息
 *	使用了 Spring Cloud 的服务发现功能，就不需要在单独添加 Admin Client 客户端，仅仅需要 Spring Boot Server ,其它内容会自动进行配置
 *	@Configuration
 *	@EnableAutoConfiguration
 *	@EnableDiscoveryClient
 *	@EnableAdminServer
 *
 *  eureka:   
 *	  instance:
 *	    leaseRenewalIntervalInSeconds: 10
 *	    health-check-url-path: /actuator/health
 *	    metadata-map:
 *	      startup: ${random.int}    #needed to trigger info and endpoint update after restart
 *	  client:
 *	    registryFetchIntervalSeconds: 5
 *	    serviceUrl:
 *	      defaultZone: ${EUREKA_SERVICE_URL:http://localhost:8761}/eureka/
 *	
 *	management:
 *	  endpoints:
 *	    web:
 *	      exposure:
 *	        include: "*"  
 *	  endpoint:
 *	    health:
 *	      show-details: ALWAYS
 *
 *
 */



@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
