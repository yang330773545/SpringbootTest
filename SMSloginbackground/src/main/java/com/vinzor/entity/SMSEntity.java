package com.vinzor.entity;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings(value="all")
public class SMSEntity implements Serializable{
	private String code;
	private String msg;
	private String obj;
	
}
