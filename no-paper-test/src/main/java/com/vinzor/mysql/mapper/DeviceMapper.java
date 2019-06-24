package com.vinzor.mysql.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.Device;

import java.util.List;

public interface DeviceMapper {

	@Select("SELECT id,meeting_room_id,type,device_name,device_ip FROM device WHERE meeting_room_id=#{meetingRoomId}")
	@Results({
		   @Result(property="id",column="id"),
		   @Result(property="type",column="type"),
		   @Result(property="deviceName",column="device_name"),
		   @Result(property="deviceIp",column="device_ip"),
		   @Result(property="meetingRoomId",column="meeting_room_id")
		}		
	)
	List<Device> selectDeviceByRoomId(int meetingRoomId);
	
	@Insert("INSERT INTO device (type,device_name,device_ip,meeting_room_id)VALUES(#{type},#{deviceName},#{deviceIp},#{meetingRoomId})")
	int insertDevice(int type, String deviceName,String deviceIp,int meetingRoomId);
	
	@Delete("DELETE FROM device WHERE id=#{id}")
	int deleteDeviceById(int id);
	
	@Update("UPDATE device SET device_name=#{deviceName} WHERE id=#{id}  ")
	int updateDeviceNameById(int id, String deviceName);
}
