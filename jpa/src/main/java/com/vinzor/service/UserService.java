package com.vinzor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinzor.dao.UserRepository;
import com.vinzor.entity.User;
import com.vinzor.entity.UserIsDog;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	public List<User> findall(){
		List<User> users=userRepository.findAll();
		return users;
	}
	public User add(User user) {
		return userRepository.save(user);
	}
	//为了方便在这里搞了
	public List<UserIsDog> findUserIsDog(){
		return userRepository.findUserIsDog();
	}
}
