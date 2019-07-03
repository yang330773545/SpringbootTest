package com.vinzor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

	@Override
	public Dog findByDogId(long dogId) {
		// TODO Auto-generated method stub
		return dogReposity.findByDogId(dogId);
	}
/*
	@Override
	public Dog finByDogIdOrDogAge(long dogId, int dogAge) {
		// TODO Auto-generated method stub
		return dogReposity.finByDogIdOrDogAge(dogId, dogAge);
	}
*/
	@Override
	public Long deleteByDogId(long dogId) {
		// TODO Auto-generated method stub
		return dogReposity.deleteByDogId(dogId);
	}

	@Override
	public Long countByDogAge(int dogAge) {
		// TODO Auto-generated method stub
		return dogReposity.countByDogAge(dogAge);
	}

	@Override
	public List<Dog> findByDogNameLike(String dogName) {
		// TODO Auto-generated method stub
		return dogReposity.findByDogNameLike(dogName);
	}

	@Override
	public List<Dog> findByDogNameIsNull() {
		// TODO Auto-generated method stub
		return dogReposity.findByDogNameIsNull();
	}

	@Override
	public Page<Dog> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		//页码数和条数
		int page=1,size=10;
		//排序方式
		Sort sort = new Sort(Direction.DESC, "id");
		pageable=PageRequest.of(page,size,sort);
		return dogReposity.findAll(pageable);
	}

}
