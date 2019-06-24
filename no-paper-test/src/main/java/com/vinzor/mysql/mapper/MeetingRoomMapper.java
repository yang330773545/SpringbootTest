package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.MeetingRoom;

public interface MeetingRoomMapper {

	@Select("SELECT id,name,cen_ip,address FROM meeting_room")
	@Results({
		 @Result(property="id",column="id"),
		 @Result(property="name",column="name"),
		 @Result(property="cenIp",column="cen_ip"),
		 @Result(property="address",column="address")
	})
	List<MeetingRoom> selectAllRoom();
	
	@Select("SELECT id,name,cen_ip,address FROM meeting_room WHERE id=#{id}")
	@Results({
		 @Result(property="id",column="id"),
		 @Result(property="name",column="name"),
		 @Result(property="cenIp",column="cen_ip"),
		 @Result(property="address",column="address")
	})
	MeetingRoom selectMeetingRoomById(int id);
	
	@Insert("INSERT INTO meeting_room (name,cen_ip,address)VALUES(#{name},#{cenIp},#{address})")
	int addRoom(String name,String cenIp,String address);
	
	@Delete("DELETE FROM meeting_room WHERE id=#{id}")
	int deleteRoomById(int id);
	
	@Update("UPDATE meeting_room SET name=#{name},cen_ip=#{cenIp},address=#{address} WHERE id=#{id}")
	int updateRoom(int id,String name,String cenIp,String address);
	
}
