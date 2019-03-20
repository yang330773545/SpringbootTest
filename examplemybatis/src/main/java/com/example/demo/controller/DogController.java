package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Dog;
import com.example.demo.service.DogService;

@RestController
@RequestMapping(value="/dog")
public class DogController {
@Autowired
DogService dogService;

	
	@RequestMapping(value="/alldog",method=RequestMethod.GET)
	public List<Dog> allDog(){
		return dogService.findAllDog();
	}
}
