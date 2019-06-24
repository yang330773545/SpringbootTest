package com.vinzor.mysql.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParticipantVote implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1455022975294210474L;

	private int id;
	
	private int voteId;
	
	private int participantId;
	
	private int voteOpinion;
}
