package com.vinzor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vinzor.mysql.entity.User;
import com.vinzor.mysql.service.UserService;

@Controller
public class LoginController {

	@Autowired
    private	UserService userService;
	
	@ResponseBody
	@GetMapping(value="/getuser")
	public User getUserByName(String userName) {
		return userService.findUserByUsername(userName);
	}
	
	@PostMapping(value="/check")
	public String check(Model model,String userName,String passWord) {
		if(userService.checkUsernamePassword(userName, passWord)) {
			model.addAttribute("id",userService.findUserByUsername(userName).getId() );
			model.addAttribute("name",userName);
			model.addAttribute("type",userService.findUserByUsername(userName).getType());
			return "redirect:welcome";
		}
		model.addAttribute("message","用户名或密码错误");
		return "redirect:login";
	}
	@ResponseBody
	@PostMapping(value="/checkuser")
	public boolean checkUser(@RequestBody String json) {
		return userService.checkUser(json);
	}
	@GetMapping(value="/login")
	public String getLoginPage(Model model) {
		return "login";
	}
	@GetMapping(value="/welcome")
	public String getWelocme() {
		return "welcome";
	}
	@GetMapping(value="/file")
	public String getFile() {
		return "file";
	}
}
