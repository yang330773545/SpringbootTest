package com.vinzor.mysql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinzor.mysql.dao.UserDao;
import com.vinzor.mysql.entity.UserEntity;
import com.vinzor.mysql.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public UserEntity selectUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(userName);
	}

}
