package com.example.demo.entity;

import java.util.List;

public class Human {

	private int humanId;
	private String humanName;
	private List<Dog> dogs;
	public int getHumanId() {
		return humanId;
	}
	public void setHumanId(int humanId) {
		this.humanId = humanId;
	}
	public String getHumanName() {
		return humanName;
	}
	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
	public List<Dog> getDogs() {
		return dogs;
	}
	public void setDogs(List<Dog> dogs) {
		this.dogs = dogs;
	}
	
	
}
