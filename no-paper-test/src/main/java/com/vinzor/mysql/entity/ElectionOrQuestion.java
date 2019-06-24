package com.vinzor.mysql.entity;

import lombok.Data;

@Data
public class ElectionOrQuestion {

	private int id;
	private int meetingId;
	private String electionContent;
	private int type;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private int kind;
	private int status;
	private int register;
	
}
