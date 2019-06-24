package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.MeetingRoom;
import com.vinzor.mysql.mapper.MeetingRoomMapper;
import com.vinzor.mysql.service.RoomService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService{

	@Autowired
	private MeetingRoomMapper meetingRoomMappe;
	private ObjectMapper mapper=new ObjectMapper();
	@Override
	public List<MeetingRoom> selectAllRoom() {
		// TODO Auto-generated method stub
		return meetingRoomMappe.selectAllRoom();
	}
	@Override
	public boolean addRoom(String json) {
		// TODO Auto-generated method stub
		try {
			Map<String,String> map= mapper.readValue(json, Map.class);
			if(meetingRoomMappe.addRoom(map.get("name"), map.get("cenIp"),map.get("address"))==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加room时解析json出错，json为："+json);
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteRoomById(int id) {
		// TODO Auto-generated method stub
		if(meetingRoomMappe.deleteRoomById(id)==1) return true;
		return false;
	}
	@Override
	public boolean updateRoom(String json) {
		// TODO Auto-generated method stub
		try {
			MeetingRoom room=mapper.readValue(json, MeetingRoom.class);
			if(meetingRoomMappe.updateRoom(room.getId(), room.getName(), room.getCenIp(),room.getAddress())==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("修改会议室json解析出错， json：" +json);
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public MeetingRoom findRoomById(int id) {
		// TODO Auto-generated method stub
		return meetingRoomMappe.selectMeetingRoomById(id);
	}

}
