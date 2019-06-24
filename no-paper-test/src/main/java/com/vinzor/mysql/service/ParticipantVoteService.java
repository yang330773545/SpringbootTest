package com.vinzor.mysql.service;

import java.util.List;
import java.util.Map;

import com.vinzor.mysql.entity.ParticipantVote;

public interface ParticipantVoteService {

	Map<String, Integer> findParticipantVote(int voteId);
	
	Map<String,List<String>> findParticipant(int voteId);
	
	boolean addParticipantVote(String json);
	
	ParticipantVote findParticipanVoteByVoteIdAndParticipantId(int voteId,int participantId);
}
