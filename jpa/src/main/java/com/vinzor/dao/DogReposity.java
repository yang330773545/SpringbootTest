package com.vinzor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinzor.entity.Dog;

public interface DogReposity extends JpaRepository<Dog, Long>{

}
