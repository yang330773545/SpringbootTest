package com.vinzor.entity;

import java.io.Serializable;

//one to one
public class UserIsDog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2320969806866327584L;
	public UserIsDog(Dog dog,User user) {
		this.dog=dog;
		this.user=user;
	}
	Dog dog;
	User user;
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
