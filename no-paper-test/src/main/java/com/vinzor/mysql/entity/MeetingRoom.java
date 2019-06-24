package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MeetingRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -961660232252671837L;
	private int id;
	private String name;
	private String cenIp;
	private String address;
}
