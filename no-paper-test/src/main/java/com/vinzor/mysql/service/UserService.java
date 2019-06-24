package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.User;

public interface UserService {
	User findUserByUsername(String userName);
	User findUserById(int id);
	boolean checkUsernamePassword(String userName,String passWord);
	boolean checkUser(String json);
	boolean addUserByUsernamePassword(String json);
	List<User> findAllUser();
	boolean deleteUserById(String json);
	boolean updatePasswordByIdUsername(String json);
}
