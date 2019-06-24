package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.Vote;

public interface VoteService {

	List<Vote> findAllVote();
	List<Vote> findVoteByMeeting(int meetingId);
	boolean addVote(String json);
	boolean updateVoteById(String json);
	boolean updateVoteStatus(String json);
	boolean deleteVoteById(int id);
}
