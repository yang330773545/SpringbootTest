package com.vinzor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//狗玩具 小孩和狗玩具1对多
@Entity
public class DogToy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dogToyId;
	
	private String dogToyName;
	

	public long getDogToyId() {
		return dogToyId;
	}

	public void setDogToyId(long dogToyId) {
		this.dogToyId = dogToyId;
	}

	public String getDogToyName() {
		return dogToyName;
	}

	public void setDogToyName(String dogToyName) {
		this.dogToyName = dogToyName;
	}
	
	
}
