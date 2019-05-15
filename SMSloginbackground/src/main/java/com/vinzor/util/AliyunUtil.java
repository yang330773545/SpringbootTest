package com.vinzor.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@SuppressWarnings(value="all")
public class AliyunUtil {

	private final String ACCESSKEY_ID="LTAImUFAkIhwsApM";
	private final String ACCESS_KEY_SECRET="ZrBIJj7VAgjMypzmgqIISAZW5ae8qu";
	private final String TEMPLATE_CODE="SMS_162733981";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StringRedisUtil stringRedisUtil;
	public boolean sendSMS(String phone) {
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String, String> aMap=new HashMap<>();
		String number=RandomStringUtils.random(6, "0123456789");
		//这里为了生成json字符串所建map 也可使用 String 拼接 "\"123\"" 转义符
		aMap.put("code", number);
		try {
			String json= mapper.writeValueAsString(aMap);
			if(stringRedisUtil.set(phone, number, 10*60L)) { 
				DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEY_ID, ACCESS_KEY_SECRET);
				IAcsClient client = new DefaultAcsClient(profile);
				CommonRequest request = new CommonRequest();
			    //request.setProtocol(ProtocolType.HTTPS);
				request.setMethod(MethodType.POST);
		        request.setDomain("dysmsapi.aliyuncs.com");
		        request.setVersion("2017-05-25");
		        request.setAction("SendSms");
		        request.putQueryParameter("RegionId", "cn-hangzhou");
		        request.putQueryParameter("PhoneNumbers", phone);
		        request.putQueryParameter("SignName", "云晫");
		        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);
		        request.putQueryParameter("TemplateParam", json);
		        CommonResponse response = client.getCommonResponse(request);
				Map<String, String> bMap = mapper.readValue(response.getData(), Map.class);	
				if(bMap.get("Message").equals("OK")) {
					stringRedisUtil.set(phone, number, 10*60L);
					return true;
				}
			}
			return false;
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("map转json出错"+e1.getMessage());
		}
	   catch (ServerException e) {
			// TODO Auto-generated catch block
			logger.error("阿里云服务器出现问题"+e.getMessage());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			logger.error("请检查代码"+e.getMessage());
		}catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("json转map出错"+e.getMessage());
			}
		return false;
	}
}
