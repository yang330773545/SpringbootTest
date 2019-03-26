package com.vinzor.mysql.service;


import com.vinzor.mysql.entity.UserEntity;


public interface UserService {

	UserEntity selectUserByUserName(String userName);
}
