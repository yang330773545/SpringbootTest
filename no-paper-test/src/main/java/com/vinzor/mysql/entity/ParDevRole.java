package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParDevRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2902109681761464270L;
	private int id;
	private int meetingId;
	private int participantId;
	private int deviceId;
	private int role;
	private int sign;
}
