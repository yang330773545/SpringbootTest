package com.yang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 	@ApiModelProperty
 *	value–字段说明 
 *	name–重写属性名字 
 *	dataType–重写属性类型 
 *	required–是否必填 
 *	example–举例说明 
 *	hidden–隐藏
 */

@ApiModel(value="user对象",description="用户对象user")
public class User {

	@ApiModelProperty(value="名字",name="name",example="gouzi")
	private String name;
	@ApiModelProperty(value="年龄",name="age",required=true)
	private int age;
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
	
}
