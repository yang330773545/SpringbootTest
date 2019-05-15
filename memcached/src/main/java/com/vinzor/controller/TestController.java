package com.vinzor.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.util.MemcachedUtil;

@RestController
public class TestController {

	@Autowired 
	MemcachedUtil memcachedUtil;
	@RequestMapping(value="/gouzi")
	public boolean add() throws InterruptedException, ExecutionException {
		return memcachedUtil.add("gou", 1000, "zi");
	}
}
