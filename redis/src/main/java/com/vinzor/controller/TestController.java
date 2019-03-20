package com.vinzor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.entity.User;
import com.vinzor.redis.StringRedisService;

@RestController
public class TestController {

	@Autowired
	StringRedisService stringRedisService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@RequestMapping(value="/test")
	public User demo() {		
		stringRedisService.set("haha", "haha", 30*60L);
		redisTemplate.opsForValue().set("user1",new User(1,"yj","123","111"));
		return (User)redisTemplate.opsForValue().get("user1");
	}
}
