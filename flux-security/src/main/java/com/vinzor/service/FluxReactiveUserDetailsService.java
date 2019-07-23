package com.vinzor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinzor.entity.User;

import reactor.core.publisher.Mono;

//@Service()
public class FluxReactiveUserDetailsService implements ReactiveUserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		// TODO Auto-generated method stub
		PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
		User defUser=userService.findUserByUsername(username);
		UserDetails user=org.springframework.security.core.userdetails.User.withUsername(defUser.getUsername())
				.password(encoder.encode(defUser.getPassword()))
				.roles("roles") // String ...参数
				.build();
		return Mono.just(user);
	}
	

}
