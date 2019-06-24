package com.vinzor.mysql.service;

import java.util.List;
import java.util.Map;

import com.vinzor.mysql.entity.EleQueVote;

public interface EleQueVoteService {

	
	EleQueVote findELeQueVoteByEleQueIdAndParticipantId(int eleQueId,int participantId);
	
	boolean addEleQueVote(String json);
	
	Map<String, Integer> findParticipantELeQueVote(int eleQueId);
	
	Map<String,List<String>> findParticipant(int eleQueId);
	
}
