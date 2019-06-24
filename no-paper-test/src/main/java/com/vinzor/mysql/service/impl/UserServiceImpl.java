package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.User;
import com.vinzor.mysql.mapper.UserMapper;
import com.vinzor.mysql.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	private ObjectMapper mapper=new ObjectMapper();
	@Override
	public User findUserByUsername(String userName) {
		return userMapper.findUserByUsername(userName);
	}
	@Override
	public boolean checkUsernamePassword(String userName, String passWord) {
		// TODO Auto-generated method stub
		User user=findUserByUsername(userName);
		if(userName.equals(user.getUserName()) && passWord.equals(user.getPassWord()) ) {
			return true;
		}
		return false;
	}
	@Override
	public boolean addUserByUsernamePassword(String json) {
		// TODO Auto-generated method stub
		User user;
		try {
			user=mapper.readValue(json, User.class);
			if(user.getType()==0) return false;
			List<User> users=this.findAllUser();
			if(user.getUserName().equals("root")) return false;
			for(int i=0;i<users.size();i++) {
				if(user.getUserName().equals(users.get(i).getUserName())) return false;
			}
			int demo=userMapper.addUser(user.getUserName(),user.getPassWord() ,user.getType(),user.getName() );
			if(demo==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加登录用户的json解析出错，json为："+json);
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userMapper.findAllUser();
	}
	@Override
	public boolean deleteUserById(String json) {
		log.info("通过id删除用户 json为"+json);
		// TODO Auto-generated method stub
		Map<String, String> map;
		try {
			map = mapper.readValue(json, HashMap.class);
			int id=Integer.parseInt(map.get("id"));
			if(id==1) return false;
			if(userMapper.deleteUserById(id)==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(id);
	}
	
	@Override
	public boolean updatePasswordByIdUsername(String json) {
		// TODO Auto-generated method stub
		User user;
		try {
			user=mapper.readValue(json, User.class);
			int demo=userMapper.updatePasswordByIdUsername(user.getId(), user.getUserName(),user.getName() ,user.getPassWord());
			if(demo==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("修改用户密码的json解析出错，json为："+json);
			e.printStackTrace();
		}
		return false;

	}
	@Override
	public boolean checkUser(String json) {
		// TODO Auto-generated method stub
		User user;
		try {
			user=mapper.readValue(json, User.class);
			User user1=findUserByUsername(user.getUserName());
			if(user1==null) return false;
			if(user.getUserName()==user1.getUserName() && user.getPassWord()==user1.getPassWord()) {
			 return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("登录发送的的json解析出错，json为："+json);
			e.printStackTrace();
		}
		return false;
	}
	
	

}
