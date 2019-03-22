package com.vinzor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinzor.dao.DogReposity;
import com.vinzor.entity.Dog;
import com.vinzor.service.DogService;

@Service
public class DogServiceImpl implements DogService {

	@Autowired
	DogReposity dogReposity;
	@Override
	public Dog add(Dog dog) {
		// TODO Auto-generated method stub		
		return dogReposity.save(dog);
	}

	@Override
	public List<Dog> findAll() {
		// TODO Auto-generated method stub
		return dogReposity.findAll();
	}

}
