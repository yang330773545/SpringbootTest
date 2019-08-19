package com.vinzor.po;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

// /teacher/mytest  这里的索引等同于关系型数据库中数据表 type主要用于分组
@Document(indexName = "teacher",type="mytest")
public class Teacher implements Serializable{


	private int id;
	private String name;
	private int age;
	private String school;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	
}
