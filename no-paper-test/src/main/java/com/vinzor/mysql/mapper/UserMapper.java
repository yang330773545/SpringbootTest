package com.vinzor.mysql.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.User;

public interface UserMapper {

	@Select("SELECT id,user_name,pass_word,type,name FROM user WHERE user_name=#{userName}")
	@Results({
		   @Result(property="id",column="id"),
		   @Result(property="userName",column="user_name"),
		   @Result(property="passWord",column="pass_word"),
		   @Result(property="type",column="type"),
		   @Result(property="name",column="name")
		}		
	)
	User findUserByUsername(String userName);
	
	@Select("SELECT id,user_name,pass_word,type,name FROM user WHERE id=#{id}")
	@Results({
		   @Result(property="id",column="id"),
		   @Result(property="userName",column="user_name"),
		   @Result(property="passWord",column="pass_word"),
		   @Result(property="type",column="type"),
		   @Result(property="name",column="name")
		}		
	)
	User findUserById(int id);
	
	@Insert("INSERT INTO user(user_name,pass_word,type,name)VALUES(#{userName},#{passWord},#{type},#{name})")
	int addUser(String userName,String passWord,int type,String name);
	
	@Select("SELECT id,user_name,pass_word,type,name FROM user WHERE type=1 ")
	@Results({
		   @Result(property="id",column="id"),
		   @Result(property="userName",column="user_name"),
		   @Result(property="passWord",column="pass_word"),
		   @Result(property="type",column="type"),
		   @Result(property="name",column="name")
		}		
	)
	List<User> findAllUser(); 
	
	@Delete("DELETE FROM user WHERE id=#{id}")
	int deleteUserById(int id);
	
	@Update("UPDATE user SET pass_word=#{passWord},name=#{name}  WHERE id=#{id} and user_name=#{userName}")
	int updatePasswordByIdUsername(int id,String userName,String name,String passWord);
}
