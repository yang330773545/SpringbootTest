package com.vinzor.service.impl;


import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.entity.RediseEntity;
import com.vinzor.entity.SMSEntity;
import com.vinzor.service.LoginService;
import com.vinzor.util.AliyunUtil;
import com.vinzor.util.HttpClientUtil;
import com.vinzor.util.StringRedisUtil;
import com.vinzor.util.ZhenziyunUtil;

@Service
@SuppressWarnings(value="all")
public class LoginServiceImpl implements LoginService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StringRedisUtil stringRedisUtil;
	@Autowired
	ZhenziyunUtil zhenziyunUtil;
	@Resource
	AliyunUtil aliyunUtil;
	
	ObjectMapper mapper=new ObjectMapper();
	@Override
	public boolean sendSMS(String json) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> amap = mapper.readValue(json, Map.class);			
			String massage=HttpClientUtil.sendT(amap.get("phone"));
			ObjectMapper objectMapper=new ObjectMapper();
			SMSEntity smsEntity=objectMapper.readValue(massage, SMSEntity.class);
			if(smsEntity.getCode().equals("200")) {
				stringRedisUtil.set(amap.get("phone"), smsEntity.getObj(), 10*60L);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("网易云信部分代码出错"+e.getMessage());
		}
		
		return false;
	}
	@Override
	public boolean sendZhenziyunSMS(String json) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> amap = mapper.readValue(json, Map.class);			
			return zhenziyunUtil.sendSMS(amap.get("phone"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("解析手机号验证码json字符串出错"+e.getMessage());
		}
		return false;
	}
	@Override
	public boolean sendAliyunSMS(String json) {
		// TODO Auto-generated method stub
		Map<String, String> amap;
		try {
			amap = mapper.readValue(json, Map.class);
			return aliyunUtil.sendSMS(amap.get("phone"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("解析手机号验证码json字符串出错"+e.getMessage());
		}
		return false;
		
	}
	
	@Override
	public boolean checkSMS(String json) {
		// TODO Auto-generated method stub
		try {	
			RediseEntity aEntity= mapper.readValue(json, RediseEntity.class);
			String phone=aEntity.getPhone();
			String verificationCode=aEntity.getCode();
			if(stringRedisUtil.get(phone)!=null && stringRedisUtil.get(phone).equals(verificationCode)) {
				 return true;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("解析手机号验证码json字符串出错"+e.getMessage());
			return false;
		}
		
		return false;
	}


}
