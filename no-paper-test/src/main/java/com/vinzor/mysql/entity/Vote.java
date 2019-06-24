package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Vote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3972337273894344252L;

	private int id;
	private int meetingId;
	private int voteType;
	private String voteInformation;
	private int voteStatus;
	
}
