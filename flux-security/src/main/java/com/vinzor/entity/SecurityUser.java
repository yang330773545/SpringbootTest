package com.vinzor.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{

	private static final long serialVersionUID = 7745124513159953361L;
	private String username;
	private String password;
	private Set<Permission> permissions;
	public SecurityUser(String username,String password,Set<String> lists) {
		// TODO Auto-generated constructor stub
		this.username=username;
		this.password=password;
		System.out.println(username);
		System.out.println(password);
		this.permissions=new HashSet<>();
		for (String s : lists) {
			System.out.println(s);
			this.permissions.add(new Permission(s));
		}
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<GrantedAuthority> sets=new HashSet<GrantedAuthority>();
		sets.addAll(this.permissions);
		return sets;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	//用户账号是否过期
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//用户账号是否被锁定
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//用户密码是否过期
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//用户是否可用
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
