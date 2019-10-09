package com.vinzor.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController{

	@GetMapping("/404")
	public String get404Page() {
		return "404";
	}
	@GetMapping("/500")
	public String get500Page() {
		return "500";
	}
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
