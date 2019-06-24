package com.vinzor.entity;

public class Demo {

	public Demo(String a,String b) {
		this.age=b;
		this.name=a;
	}
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}
