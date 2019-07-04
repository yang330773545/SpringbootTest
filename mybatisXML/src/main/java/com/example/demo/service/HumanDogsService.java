package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Human;
import com.example.demo.mapper.HumanDogs;

@Service
public class HumanDogsService {

	@Autowired
	HumanDogs humanDogs;
	public Human getHumanDogsByHumanId(int humanId) {
		return humanDogs.findHumanDogsByHumanId(humanId);
	}
}
