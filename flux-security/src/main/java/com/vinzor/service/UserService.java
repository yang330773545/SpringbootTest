package com.vinzor.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vinzor.entity.User;

@Service
public class UserService {

	//  模拟数据
	public User findUserByUsername(String username){
		Set<String> sets=new HashSet<>();
		sets.add("ROLE_USER");
		sets.add("ROLE_ADMIN");
		sets.add("READ");
		User user=new User();
		user.setUsername(username);
		user.setPassword("$2a$10$BEDAJyp8zYv0KANCJsfSCu1zD/.TZXkGpH.p1UtPaEco3aE5yAlG6");
		//user.setPassword(username);
		user.setPeimissions(sets);
		return user;
	}
}
