package com.yang.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.entity.GitHub;

@Controller
public class Oauth2Controller {


	private RestTemplate restTemplate=new RestTemplate();
	private final String clientId="ec23a5ff78719018d89c";
	private final String clientSecret="ebd2edbe3a925e4a3ff23008837089998ed7195a";
	private final String url="https://github.com/login/oauth/access_token";
	private final ObjectMapper mapper=new ObjectMapper();
	private String code;
	
	@ResponseBody
	@GetMapping("/oauth/redirect")
	public String code(String code) {
		this.code=code;
		return getToken();
	}

	@GetMapping()
	public String login() {
		return "index";
	}
	/*
	public String getToken() {
		if(code==null) return null;
		GitHub hub=new GitHub(clientId, clientSecret, code);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<GitHub> entity = new HttpEntity<GitHub>(hub, headers);		
		ResponseEntity<String> responseEntity= restTemplate.postForEntity(url, entity, String.class);		
		String tokenString = responseEntity.getBody();
		return tokenString;
	}*/
	public String getToken() {
		if(code==null) return null;
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		paramMap.add("client_id", clientId);
		paramMap.add("client_secret", clientSecret);
		paramMap.add("code", code);
		String tokenString=restTemplate.postForObject(url, paramMap, String.class);
		return tokenString;
	}
}
