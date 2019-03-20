package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.entity.Dog;

public interface DogMapper {
	//查询狗子
	@Select("SELECT * FROM dog")
	@Results({
	   @Result(property="dogName",column="dog_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
	   @Result(property="dogAge",column="dog_age"),
	   @Result(property="id",column="id")
	})
	List<Dog> getAllDog();
	//插入狗子
	@Insert("INSERT INTO dog(dog_name,dog_age) VALUES(#{dogName},#{dogAge})")
	void insert(Dog dog);
	 
}
