package com.vinzor.entity;


import lombok.Data;

//lombok不要再写另一个getter或equals方法，使用一个注释，您的类具有一个功能齐全的构建器，自动化您的日志记录变量等等。
@Data
@SuppressWarnings(value = { "all" })

public class Users {

	private Long id;
	private String userName;
	private String nickName;
	private String passWord;
	
}
