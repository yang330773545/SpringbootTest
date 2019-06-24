package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.Vote;

public interface VoteMapper {

	@Select("SELECT id,meeting_id,vote_type,vote_information,vote_status FROM vote")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="voteType",column="vote_type"),
		@Result(property="voteInformation",column="vote_information"),
		@Result(property="voteStatus",column="vote_status")
	})
	List<Vote> findAllVote();
	
	@Select("SELECT id,meeting_id,vote_type,vote_information,vote_status FROM vote WHERE meeting_id=#{meetingId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="voteType",column="vote_type"),
		@Result(property="voteInformation",column="vote_information"),
		@Result(property="voteStatus",column="vote_status")
	})
	List<Vote> findVoteByMeeting(int meetingId);
	
	@Insert("INSERT INTO vote (meeting_id,vote_type,vote_information)VALUES(#{meetingId},#{voteType},#{voteInformation})")
	int addVote(Vote vote);
	
	@Update("UPDATE vote SET vote_type=#{voteType},vote_information=#{voteInformation} WHERE id=#{id}")
	int updateVoteById(Vote vote);
	
	@Update("UPDATE vote SET vote_status=#{voteStatus} WHERE id=#{id}")
	int updateVoteStatus(Vote vote);
	
	@Delete("DELETE FROM vote WHERE id=#{id}")
	int deleteVoteById(int id);
}
