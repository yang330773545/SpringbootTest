package com.vinzor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.entity.User;
import com.vinzor.mapper.UserMapper;
import com.vinzor.util.JwtUtil;

@RestController
public class TestController {

	@Autowired
	UserMapper userMapper;
	@RequestMapping(value="/test")
	public List<User> test(){
		return userMapper.selectList(null);
	}
	@RequestMapping(value="/login")
	public String token(String user) {
		return JwtUtil.generateToken(user);
	}
	@RequestMapping(value="/look")
	public String check(String jwt) {
		return JwtUtil.validateToken(jwt);
	}
}
