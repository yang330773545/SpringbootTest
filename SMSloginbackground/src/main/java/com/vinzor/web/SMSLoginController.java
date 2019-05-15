package com.vinzor.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vinzor.service.LoginService;


@RestController
public class SMSLoginController {

	@Autowired
	LoginService loginService;
	@RequestMapping(value="/getsms",method=RequestMethod.POST)
	public boolean getSMS(@RequestBody String json) {
		//网易云信 没有充钱 所以没测试 
		//return loginService.sendSMS(json);
		//榛子云 个人测试用户 未升级 短信会带  “测试”
		//return loginService.sendZhenziyunSMS(json);
		//阿里云
		return loginService.sendAliyunSMS(json);
	}
	@RequestMapping(value="/check",method=RequestMethod.POST)
	public boolean login(@RequestBody String json) {
		return loginService.checkSMS(json);
	}
	
}
