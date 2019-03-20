package com.vinzor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.entity.User;
import com.vinzor.mongodb.UserRepository;

@RestController
public class TestController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/demo")
	public String demo() {
		userRepository.saveUser(new User(1,"yd","123","111"));
		return "look";
	}
}
