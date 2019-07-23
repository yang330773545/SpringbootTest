package com.vinzor.entity;


import java.util.Set;

public class User {

	private String username;
	private String password;
	private Set<String> peimissions;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getPeimissions() {
		return peimissions;
	}
	public void setPeimissions(Set<String> peimissions) {
		this.peimissions = peimissions;
	}
	
	
}
