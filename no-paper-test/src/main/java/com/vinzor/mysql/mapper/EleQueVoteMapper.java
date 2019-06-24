package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.vinzor.mysql.entity.EleQueVote;

public interface EleQueVoteMapper {

	@Select("SELECT id,ele_que_id,participant_id,choose_1,choose_2,choose_3,choose_4,choose_5 FROM ele_que_vote WHERE ele_que_id=#{eleQueId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="eleQueId",column="ele_que_id"),
		@Result(property="participantId",column="participant_id"),
		@Result(property="choose1",column="choose_1"),
		@Result(property="choose2",column="choose_2"),
		@Result(property="choose3",column="choose_3"),
		@Result(property="choose4",column="choose_4"),
		@Result(property="choose5",column="choose_5")
	})
	List<EleQueVote> findELeQueVoteByEleQueId(int eleQueId);
	
	@Select("SELECT id,ele_que_id,participant_id,choose_1,choose_2,choose_3,choose_4,choose_5 FROM ele_que_vote WHERE ele_que_id=#{eleQueId} and participant_id=#{participantId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="eleQueId",column="ele_que_id"),
		@Result(property="participantId",column="participant_id"),
		@Result(property="choose1",column="choose_1"),
		@Result(property="choose2",column="choose_2"),
		@Result(property="choose3",column="choose_3"),
		@Result(property="choose4",column="choose_4"),
		@Result(property="choose5",column="choose_5")
	})
	EleQueVote findELeQueVoteByEleQueIdAndParticipantId(int eleQueId,int participantId);
	
	@Insert("INSERT INTO ele_que_vote (ele_que_id,participant_id,choose_1,choose_2,choose_3,choose_4,choose_5)VALUES(#{eleQueId},#{participantId},#{choose1},#{choose2},#{choose3},#{choose4},#{choose5})")
	int addEleQueVote(EleQueVote eleQueVote);
	
}
