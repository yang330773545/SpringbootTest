package com.yang.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

	private static final long serialVersionUID = -3517531506659060031L;

	private String token;
	
	public JWTToken(String token) {
		// TODO Auto-generated constructor stub
		this.token=token;
	}
	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return token;
	}

}
