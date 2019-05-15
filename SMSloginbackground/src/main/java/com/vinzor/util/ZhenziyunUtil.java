package com.vinzor.util;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.entity.ZhenziyunEntity;
import com.zhenzi.sms.ZhenziSmsClient;

@Component
public class ZhenziyunUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String API_URL="https://sms_developer.zhenzikj.com";
	private static final String APP_ID="101173";
	private static final String APP_SECRET="7af8a2e2-09c7-478a-a608-c29bce0dbc2e";
	@Autowired
	StringRedisUtil stringRedisUtil;
	public boolean sendSMS(String phone) {
		ZhenziSmsClient client=new ZhenziSmsClient(API_URL, APP_ID, APP_SECRET);
		try {
			String number=RandomStringUtils.random(6, "0123456789");
			String result = client.send(phone, "您的云桌面验证码为"+number);
			if(stringRedisUtil.set(phone, number, 15*60L)) {
				ObjectMapper objectMapper=new ObjectMapper();
				ZhenziyunEntity zhenziyunEntity=objectMapper.readValue(result,ZhenziyunEntity.class);			
				if(zhenziyunEntity.getCode().equals("0")) {
					return true;
				}
			}		
		}catch(IOException e) {
			logger.error("json转换实体类出错"+e.getMessage());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("榛子云错误"+e.getMessage());
		}
		return false;
	}
}
