package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2301214689215646859L;
	private int id;
	private String userName;
	private String passWord;
	private int type;
	private String name;
}
