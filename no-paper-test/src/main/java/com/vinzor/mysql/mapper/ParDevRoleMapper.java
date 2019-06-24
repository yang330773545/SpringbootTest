package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.ParDevRole;

public interface ParDevRoleMapper {

	@Select("SELECT id,meeting_id,participant_id,device_id,role,sign FROM meeting_participant_device WHERE meeting_id=#{meetingId}")
	@Results({
		 @Result(property="id",column="id"),
		 @Result(property="meetingId",column="meeting_id"),
		 @Result(property="participantId",column="participant_id"),
		 @Result(property="deviceId",column="device_id"),
		 @Result(property="role",column="role"),
		 @Result(property="sign",column="sign")
	})
	List<ParDevRole> finDevRolesByMeetingId(int meetingId);
	
	@Update("UPDATE meeting_participant_device SET sign=#{sign} WHERE meeting_id=#{meetingId} and participant_id=#{participantId}")
	int UpdateSign(int sign,int meetingId,int participantId);
	
	@Update("UPDATE meeting_participant_device SET participant_id=#{participantId},device_id=#{deviceId},role=#{role} WHERE id=#{id} and meeting_id=#{meetingId}")
	int updateDevRoles(ParDevRole parDevRole);
	
	@Insert("INSERT INTO meeting_participant_device (meeting_id,participant_id,device_id,role)VALUES(#{meetingId},#{participantId},#{deviceId},#{role})")
	int addDevRoles(ParDevRole parDevRole);
	
	@Delete("DELETE FROM meeting_participant_device WHERE id=#{id}")
	int deleteDevRoles(int id);
}
