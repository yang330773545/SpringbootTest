package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.User;
import com.vinzor.mysql.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	//添加用户 type必须为1
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public boolean addUserByUsernamePassword(@RequestBody String json) {
		return userService.addUserByUsernamePassword(json);
	}
	//查询type=1的用户
	@GetMapping(value="/selectalluser")
	public List<User> selectAllUser(){
		return userService.findAllUser();
	}
	//通过id删除用户不能删除id=1
	@PostMapping(value="/deleteuserbyid")
	public boolean deleteUserById(@RequestBody String json) {
		return userService.deleteUserById(json);
	}
	//修改除了id为1 用户的密码
	@PostMapping(value="/updatenameandpassword")
	public boolean updatePassword(@RequestBody String json) {
		return userService.updatePasswordByIdUsername(json);
	}
	@GetMapping(value="/id/{id}/user")
	public User selectUserById(@PathVariable int id){
		return userService.findUserById(id);
	}
	
}
