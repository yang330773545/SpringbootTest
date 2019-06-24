package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Participant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8170319563852419193L;
	private int id;
	private String name;
	private int sex;
	private String phoneNumber;
	private String wxCode;
	private String position;
}
