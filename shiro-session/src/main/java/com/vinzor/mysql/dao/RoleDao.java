package com.vinzor.mysql.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.vinzor.shiro.Role;

public interface RoleDao {

	@Select("SELECT * FROM role WHERE id = #{id}")
	@Results({
		@Result(property = "role1",  column = "role1"),
		@Result(property = "role2", column = "role2")
	})
	Role findRoleById(long id);
}
