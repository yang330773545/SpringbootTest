package com.vinzor.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vinzor.entity.Dog;

public interface DogService {

	 Dog add(Dog dog);
	 
	 List<Dog> findAll();
	 
	 Dog findByDogId(long dogId);
		
	// Dog finByDogIdOrDogAge(long dogId,int dogAge);
		
	 Long deleteByDogId(long dogId);
		
	 Long countByDogAge(int dogAge);
		
	 List<Dog> findByDogNameLike(String dogName);
		
	 List<Dog> findByDogNameIsNull();
		
	 Page<Dog> findAll(Pageable pageable);

}
