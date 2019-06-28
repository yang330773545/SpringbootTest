package com.yang.entity;

public class GitHub {

	private String client_id;
	private String clie_secret;
	private String code;
	
	public GitHub(String clientId,String clientSecret,String code) {
		this.clie_secret=clientSecret;
		this.client_id=clientId;
		this.code=code;		
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClie_secret() {
		return clie_secret;
	}
	public void setClie_secret(String clie_secret) {
		this.clie_secret = clie_secret;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
