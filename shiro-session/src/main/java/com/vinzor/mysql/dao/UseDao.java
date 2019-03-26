package com.vinzor.mysql.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.vinzor.shiro.User;

public interface UseDao {

	@Select("SELECT * FROM user WHERE id = #{id}")
	@Results({
		@Result(property = "user1",  column = "user1"),
		@Result(property = "user2", column = "user2")
	})
	User findUserById(long id);
}
