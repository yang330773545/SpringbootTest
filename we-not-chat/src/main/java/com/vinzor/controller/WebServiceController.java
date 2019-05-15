package com.vinzor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebServiceController {

	@RequestMapping(value="/web")
	public String web() {
		return "index";
	}
}
