package com.vinzor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinzor.entity.SecurityUser;
import com.vinzor.entity.User;

@Service
public class WebUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userService.findUserByUsername(username);
		UserDetails userDetails=new SecurityUser(user.getUsername(),user.getPassword(),user.getPeimissions());
		return userDetails;
	}

}
