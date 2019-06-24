package com.vinzor.mysql.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.vinzor.mysql.entity.Meeting;

public interface MeetingMapper {

	@Select("SELECT id,meeting_room_id,start_time,end_time,appointment_persion,way_of_sign,confidentiality FROM meeting")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingRoomId",column="meeting_room_id"),
		@Result(property="startTime",column="start_time",jdbcType=JdbcType.TIMESTAMP),
		@Result(property="endTime",column="end_time",jdbcType=JdbcType.TIMESTAMP),
		@Result(property="appointmentPersion",column="appointment_persion"),
		@Result(property="wayOfSign",column="way_of_sign"),
		@Result(property="confidentiality",column="confidentiality"),
		@Result(property="signBackId",column="sign_back_id"),
		@Result(property="projectionBackId",column="projection_back_id"),
		@Result(property="tableBackId",column="table_back_id"),
		@Result(property="signLogoId",column="sign_logo_id"),
		@Result(property="projectionLogoId",column="projection_logo_id")
	})
	List<Meeting> getAllMeetings();
	@Select("SELECT id,meeting_room_id,start_time,end_time,appointment_persion,way_of_sign,confidentiality FROM meeting WHERE meeting_room_id=#{meetingRoomId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingRoomId",column="meeting_room_id"),
		@Result(property="startTime",column="start_time"),
		@Result(property="endTime",column="end_time"),
		@Result(property="appointmentPersion",column="appointment_persion"),
		@Result(property="wayOfSign",column="way_of_sign"),
		@Result(property="confidentiality",column="confidentiality"),
		@Result(property="signBackId",column="sign_back_id"),
		@Result(property="projectionBackId",column="projection_back_id"),
		@Result(property="tableBackId",column="table_back_id"),
		@Result(property="signLogoId",column="sign_logo_id"),
		@Result(property="projectionLogoId",column="projection_logo_id")
	})
	List<Meeting> getMeetingByRoomId(int meetingRoomId);
	
	@Select("SELECT id,meeting_room_id,start_time,end_time,appointment_persion,way_of_sign,confidentiality FROM meeting WHERE id=#{id}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingRoomId",column="meeting_room_id"),
		@Result(property="startTime",column="start_time"),
		@Result(property="endTime",column="end_time"),
		@Result(property="appointmentPersion",column="appointment_persion"),
		@Result(property="wayOfSign",column="way_of_sign"),
		@Result(property="confidentiality",column="confidentiality"),
		@Result(property="signBackId",column="sign_back_id"),
		@Result(property="projectionBackId",column="projection_back_id"),
		@Result(property="tableBackId",column="table_back_id"),
		@Result(property="signLogoId",column="sign_logo_id"),
		@Result(property="projectionLogoId",column="projection_logo_id")
	})
	Meeting getMeetingById(int id);
	
	@Insert("INSERT INTO meeting (meeting_room_id,start_time,end_time,appointment_persion,way_of_sign,confidentiality)VALUES(#{meetingRoomId},#{startTime},#{endTime},#{appointmentPersion},#{wayOfSign},#{confidentiality})")
	int addMeeting(Meeting meeting);
	
	@Delete("DELETE FROM meeting WHERE id=#{id}")
	int deleteMeetingById(int id);
	
	@Update("UPDATE meeting SET sign_back_id=#{signBackId},sign_logo_id=#{signLogoId} WHERE id=#{id}  ")
	int updateMeetingSign(int signBackId,int signLogoId,int id);
	
	@Update("UPDATE meeting SET projection_back_id=#{projectionBackId},projection_logo_id=#{projectionLogoId} WHERE id=#{id}")
	int updateMeetingProjection(int projectionBackId,int projectionLogoId,int id);
	
	@Update("UPDATE meeting SET table_back_id=#{tableBackId} WHERE id=#{id} ")
	int updateMeetingTable(int tableBackId,int id);
	
	@Update("UPDATE meeting SET theme=#{theme} WHERE id=#{id}")
	int updateMeetingTheme(int theme,int id);
	
	@Update("UPDATE meeting SET scheme=#{scheme} WHERE id=#{id}")
	int updateMeetingScheme(String scheme,int id);
	
	@Update("UPDATE meeting SET start_time=#{startTime},end_time=#{endTime},appointment_persion=#{appointmentPersion},way_of_sign=#{wayOfSign},confidentiality=#{confidentiality} WHERE id=#{id}")
	int updateMeeting(LocalDateTime startTime,LocalDateTime endTime,String appointmentPersion,int wayOfSign,int confidentiality,int id);
}
