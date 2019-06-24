package com.vinzor.mysql.entity;

import lombok.Data;

@Data
public class EleQueVote {

	private int id;
	private int eleQueId;
	private int participantId;
	private int choose1;
	private int choose2;
	private int choose3;
	private int choose4;
	private int choose5;
	
}
