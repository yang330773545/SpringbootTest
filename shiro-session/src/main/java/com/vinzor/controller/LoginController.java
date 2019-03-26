package com.vinzor.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class LoginController {

	
	@RequestMapping(value="/check")
	public String login(String userName,String passWord) {
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken uToken=new UsernamePasswordToken(userName, passWord, false);		
		try {
			subject.login(uToken);
			return "已经成功登录";	
		} catch (AuthenticationException e) {
			// TODO: handle exception
			uToken.clear();        
	        return "用户名或者密码错误";
		}
	}
	@RequiresRoles("admin")
	@RequestMapping(value="/test")	
	public String Test() {
		return "看得到";
	}
}
