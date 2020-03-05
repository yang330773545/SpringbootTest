package com.vinzor.po;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// /teacher/mytest  这里的索引等同于关系型数据库中数据表 type主要用于分组
@Document(indexName = "teacher",type="mytest")
public class Teacher implements Serializable{
	private static final long serialVersionUID = 123123123123213L;
	private int id;
	//  type自动检测属性的类型，可以根据实际情况自己设置
	@Field(type = FieldType.Keyword)
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
