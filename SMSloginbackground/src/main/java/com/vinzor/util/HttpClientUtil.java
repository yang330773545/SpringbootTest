package com.vinzor.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {
	//网易云信请求路径URl
	private static String PROVIDER_URL="https://api.netease.im/sms/sendcode.action";
	//网易云信分配的账号
	private static String APP_KEY="b7f2e9abb1075f0f454b5ec6c8a3fab9";
	////网易云信分配的密钥
	private static String APP_SECRET="0503c41445f6";
	//验证码的长度
	private static String CODELEN="6";
	//模板id
	private static String TEMPLATE_ID="3057527";
	//随机数
	private static String NONCE=RandomStringUtils.random(6, "0123456789");;
	
	public static String sendT(String phoneNumber) {		
		String MOBILE=phoneNumber;
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(PROVIDER_URL);
	    String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
      
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", TEMPLATE_ID));
        nvps.add(new BasicNameValuePair("mobile", MOBILE));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));

        try {
        	httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            /*
             * 1.返回执行结果，打印结果一般会200、315、403、404、413、414、500
             * 2.具体的code有问题的可以参考官网的Code状态表
             */	
            //System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            return  EntityUtils.toString(response.getEntity(), "utf-8");
			
		} catch (Exception e) {
			return "http请求执行错误";
		}	
	}
	
}
