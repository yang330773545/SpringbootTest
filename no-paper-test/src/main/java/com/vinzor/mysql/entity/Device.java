package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2699650142583171593L;
	private int id;
	private int type;
	private int meetingRoomId;
	private String deviceName;
	private String deviceIp;
}
