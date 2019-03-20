package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Dog;
import com.example.demo.mapper.DogMapper;

@Service
public class DogService {

	@Autowired
	DogMapper dogMapper;
	public List<Dog> findAllDog(){
		return dogMapper.getAllDog();
	}
	
}
