package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.ElectionOrQuestion;

public interface ElectionOrQuestionMapper {

	@Select("SELECT id,meeting_id,type,election_content,option_1,option_2,option_3,option_4,option_5,kind,status,register FROM election_question WHERE meeting_id=#{meetingId} and type=1")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="electionContent",column="election_content"),
		@Result(property="type",column="type"),
		@Result(property="option1",column="option_1"),
		@Result(property="option2",column="option_2"),
		@Result(property="option3",column="option_3"),
		@Result(property="option4",column="option_4"),
		@Result(property="option5",column="option_5"),
		@Result(property="kind",column="kind"),
		@Result(property="status",column="status"),
		@Result(property="register",column="register")
	})
	List<ElectionOrQuestion> findEleByMeetingId(int meetingId);
	
	@Select("SELECT id,meeting_id,type,election_content,option_1,option_2,option_3,option_4,option_5,kind,status,register FROM election_question WHERE meeting_id=#{meetingId} and type=2")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="electionContent",column="election_content"),
		@Result(property="type",column="type"),
		@Result(property="option1",column="option_1"),
		@Result(property="option2",column="option_2"),
		@Result(property="option3",column="option_3"),
		@Result(property="option4",column="option_4"),
		@Result(property="option5",column="option_5"),
		@Result(property="kind",column="kind"),
		@Result(property="status",column="status"),
		@Result(property="register",column="register")
	})
	List<ElectionOrQuestion> findQueByMeetingId(int meetingId);
	
	@Insert("INSERT INTO election_question (meeting_id,type,election_content,option_1,option_2,option_3,option_4,option_5,kind,register)VALUES(#{meetingId},"
			+ "#{type},#{electionContent},#{option1},#{option2},#{option3},#{option4},#{option5},#{kind},#{register})")
	int addEleOrQue(int meetingId,int type,String electionContent,String option1,String option2,String option3,String option4,String option5,int kind,int register);

	@Delete("DELETE FROM election_question WHERE id=#{id}")
	int deleteEleOrQueById(int id);
	
	@Update("UPDATE election_question SET election_content=#{electionContent},option_1=#{option1},option_2=#{option2},option_3=#{option3},option_4=#{option4},option_5=#{option5},kind=#{kind},register=#{register} WHERE id=#{id}")
	int updateEleOrQueById(String electionContent,String option1,String option2,String option3,String option4,String option5,int kind,int id,int register);
	
	@Update("UPDATE election_question SET status=#{status} WHERE id=#{id}")
	int updateEleOrQueStatusById(int status,int id);
}
