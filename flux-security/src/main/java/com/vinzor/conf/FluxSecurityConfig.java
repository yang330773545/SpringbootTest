package com.vinzor.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.vinzor.service.FluxReactiveUserDetailsService;

@Configuration
@EnableWebFluxSecurity
public class FluxSecurityConfig {
	
	//@Autowired
	//@Qualifier("fluxReactiveUserDetailsService")
	//private FluxReactiveUserDetailsService fluxReactiveUserDetailsService;
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
		http
			.authorizeExchange()
			.pathMatchers("/loginPage").permitAll()  // 无需权限
			.anyExchange().authenticated()
			.and()
			.httpBasic().and()
			.formLogin().loginPage("/loginPage");//登录页
		http
	      .csrf().disable();	//默认情况下，Java配置中启用 CSRF保护。如果我们需要，我们可以禁用它
		return http.build();
	}
	@Bean
	public MapReactiveUserDetailsService userDetailsService(){
		UserDetails users=User.withDefaultPasswordEncoder() // 生产不安全仅在示例中使用
				.username("user")
				.password("user")
				.roles("USER")
				.build();
		return new MapReactiveUserDetailsService(users);
	}
}
