package com.yang.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.yang.doman.po.Role;
import com.yang.doman.po.User;

@Service
public class UserService {

	public User findUserByUserName(String userName) {
		User user=new User();
		user.setUserId(1);
		user.setUserName(userName);
		user.setPassWord("pass");
		return user;
	}
	//模拟角色
	public Set<Role> findRoleByUserName(String userName){
		Role role1=new Role();
		role1.setRole("admin");
		role1.setRoleId(1);
		Role role2=new Role();
		role2.setRole("user");
		role2.setRoleId(2);
		Set<Role> set=new HashSet<>();
		set.add(role1);
		set.add(role2);
		return set;
	}
	//模拟权限
	public Set<String> findPermissionByUserName(String userName){		
		Set<String> set=new HashSet<>();
		set.add("look");	
		set.add("find");
		return set;
	}
	
}
