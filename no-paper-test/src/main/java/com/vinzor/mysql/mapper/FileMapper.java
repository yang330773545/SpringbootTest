package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.vinzor.mysql.entity.MyFile;

public interface FileMapper {

	@Select("SELECT id,name,path,type,meeting_id,participant_id FROM file ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="path",column="path"),
		@Result(property="type",column="type"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="participantId",column="participant_id")
	})
	List<MyFile> findAllFile();
	
	@Select("SELECT id,name,path FROM file WHERE type=#{type}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="path",column="path"),
		@Result(property="type",column="type"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="participantId",column="participant_id")
	})
	List<MyFile> findFileByType(int type);
	
	@Select("SELECT id,name,path,type,meeting_id,upload_participant_id FROM file WHERE id=#{id}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="path",column="path"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="participantId",column="participant_id")
	})
	MyFile findFileById(int id);
	
	@Insert("INSERT INTO file (name,path,type)VALUES(#{name},#{path},#{type})")
	int addFile(String name,String path,int type);
	
	@Delete("DELETE FROM file WHERE id=#{id}")
	int deleteFile(int id);
	
	@Select("SELECT id,name,path,type,meeting_id,upload_participant_id FROM file WHERE meeting_id=#{meetingId} and type=3")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="path",column="path"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="participantId",column="participant_id")
	})
	List<MyFile> findMeetingFileByType(int meetingId);
	
	@Select("SELECT id,name,path,type,meeting_id,upload_participant_id FROM file WHERE meeting_id=#{meetingId} and type=4")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="path",column="path"),
		@Result(property="meetingId",column="meeting_id"),
		@Result(property="participantId",column="participant_id")
	})
	List<MyFile> findSharedFileByType(int meetingId);
	
	@Insert("INSERT INTO file (name,path,type,meeting_id)VALUES(#{name},#{path},3,#{meetingId})")
	int addMeetingFile(String name,String path,int meetingId);
	
	@Insert("INSERT INTO file (name,path,type,meeting_id,upload_participant_id)VALUES(#{name},#{path},4,#{meetingId},#{uploadParticipantId})")
	int addSharedFile(String name,String path,int meetingId,int uploadParticipantId);
}
