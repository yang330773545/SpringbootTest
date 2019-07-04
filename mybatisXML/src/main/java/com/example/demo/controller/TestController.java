package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Father;
import com.example.demo.entity.Human;
import com.example.demo.mapper.FatherMapper;
import com.example.demo.service.HumanDogsService;

@RestController
public class TestController {

	@Autowired
	HumanDogsService humanDogsService;
	@Autowired
	FatherMapper fatherMapper;
	@RequestMapping("/get")
	public Human get() {
		return humanDogsService.getHumanDogsByHumanId(1);
	}
	@RequestMapping("/get1")
	public Father get1() {
		return fatherMapper.findFatherSonByFatherId(1);
	}
	
}
