package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.MeetingRoom;

public interface RoomService {

	List<MeetingRoom> selectAllRoom();
	boolean addRoom(String json);
	boolean deleteRoomById(int id);
	boolean updateRoom(String json);
	MeetingRoom findRoomById(int id);
}
