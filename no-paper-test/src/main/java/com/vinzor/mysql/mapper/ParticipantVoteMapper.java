package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.vinzor.mysql.entity.ParticipantVote;

public interface ParticipantVoteMapper {

	@Select("SELECT id,vote_id,participant_id,vote_opinion FROM participant_vote WHERE vote_id=#{voteId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="voteId",column="vote_id"),
		@Result(property="participantId",column="participant_id"),
		@Result(property="voteOpinion",column="vote_opinion")
	})
	List<ParticipantVote> ParticipantVoteByVoteId(int voteId);
	
	@Insert("INSERT INTO participant_vote (vote_id,participant_id,vote_opinion)VALUES(#{voteId},#{participantId},#{voteOpinion})")
	int addParticipantVote(ParticipantVote participantVote);
	
	@Select("SELECT id,vote_id,participant_id,vote_opinion FROM participant_vote WHERE vote_id=#{voteId} and participant_id=#{participantId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="voteId",column="vote_id"),
		@Result(property="participantId",column="participant_id"),
		@Result(property="voteOpinion",column="vote_opinion")
	})
	ParticipantVote findParticipanVoteByVoteIdAndParticipantId(int voteId,int participantId);
}
