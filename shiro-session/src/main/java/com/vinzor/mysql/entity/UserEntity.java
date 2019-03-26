package com.vinzor.mysql.entity;

import java.io.Serializable;

import com.vinzor.mysql.Enum.UserSexEnum;

public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String passWord;
	private UserSexEnum userSex;
	private String userName;
	private String nickName;
	
	public UserSexEnum getUserSex() {
		return userSex;
	}
	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
