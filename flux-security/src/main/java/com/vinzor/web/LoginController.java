package com.vinzor.web;

import java.io.IOException;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {
/*
	@RequestMapping(value="/login")
	public String loginPage() {
		return "login";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/test")
	@ResponseBody
	public String test() {
		return "登录了";
	}
	@RequestMapping("/login/error")
	@ResponseBody
	public void loginError(HttpServletRequest request, HttpServletResponse response) {
	    response.setContentType("text/html;charset=utf-8");
	    AuthenticationException exception =
	            (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	    try {
	        response.getWriter().write(exception.toString());
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	*/
	@RequestMapping(value="/loginPage")
	public String loginPage() {
		return "loginPage";
	}
	@Secured("ROLE_USER")
	@RequestMapping(value="/testwebflux")
	@ResponseBody
	public String test() {
		return "登录了";
	}
}
