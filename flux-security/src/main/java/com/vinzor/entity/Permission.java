package com.vinzor.entity;

import org.springframework.security.core.GrantedAuthority;

public class Permission implements GrantedAuthority{

	private static final long serialVersionUID = -1119048505558071426L;
	private String authority;
	public Permission(String authority) {
		// TODO Auto-generated constructor stub
		this.authority=authority;
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
