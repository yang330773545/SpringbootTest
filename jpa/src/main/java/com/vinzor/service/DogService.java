package com.vinzor.service;


import java.util.List;

import com.vinzor.entity.Dog;

public interface DogService {

	 Dog add(Dog dog);
	 List<Dog> findAll();
}
