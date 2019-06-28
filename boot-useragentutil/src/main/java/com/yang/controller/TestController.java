package com.yang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

public class TestController {

	public List<?> getAgent(HttpServletRequest request){
		List<String> list=new ArrayList<>();
		//获取浏览器信息
		String ua = request.getHeader("User-Agent");
		//转成UserAgent对象
		UserAgent userAgent = UserAgent.parseUserAgentString(ua); 
		//获取浏览器信息
		Browser browser = userAgent.getBrowser();  
		//获取系统信息
		OperatingSystem os = userAgent.getOperatingSystem();
		//系统名称
		String system = os.getName();
		//浏览器名称
		String browserName = browser.getName();
		return list;
	}
}
