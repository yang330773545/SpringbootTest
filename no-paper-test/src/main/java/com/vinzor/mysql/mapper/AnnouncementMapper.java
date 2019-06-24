package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.Announcement;

public interface AnnouncementMapper {

	@Select("SELECT id,meeting_id,anno_title,anno_content,logo_file_id,background_file_id FROM announcement WHERE meeting_id=#{meetingId}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="annoTitle",column="anno_title"),
		@Result(property="annoContent",column="anno_content"),
		@Result(property="logoFileId",column="logo_file_id"),
		@Result(property="backgroundFileId",column="background_file_id")
	})
	List<Announcement> findAnnouncementByMeetingId(int meetingId);
	
	@Select("SELECT id,meeting_id,anno_title,anno_content,logo_file_id,background_file_id FROM announcement WHERE id=#{id}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="annoTitle",column="anno_title"),
		@Result(property="annoContent",column="anno_content"),
		@Result(property="logoFileId",column="logo_file_id"),
		@Result(property="backgroundFileId",column="background_file_id")
	})
	Announcement findAnnouncementById(int id);
	
	@Insert("INSERT INTO announcement (meeting_id,anno_title,anno_content,logo_file_id,background_file_id)VALUES(#{meetingId},#{annoTitle},#{annoContent},#{logoFileId},#{backgroundFileId})")
	int addAnnouncement(int meetingId,String annoTitle,String annoContent,int logoFileId,int backgroundFileId);
	
	@Update("UPDATE announcement SET anno_title=#{annoTitle},anno_content=#{annoContent},logo_file_id=#{logoFileId},background_file_id=#{backgroundFileId}  WHERE id=#{id}")
	int updateAnnouncementById(int id,String annoTitle,String annoContent,int logoFileId,int backgroundFileId);

	@Delete("DELETE FROM announcement WHERE id=#{id}")
	int deleteAnnouncementById(int id);
}
