package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.MeetingRoom;
import com.vinzor.mysql.service.RoomService;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;
	@GetMapping(value="/getallroom")
	public List<MeetingRoom> getAllRoom(){
		return roomService.selectAllRoom();
	}
	@PostMapping(value="/addroom")
	public boolean addRoom(@RequestBody String json) {
		return roomService.addRoom(json);
	}
	@PostMapping(value="/updateroom")
	public boolean updateRoom(@RequestBody String json) {
		return roomService.updateRoom(json);
	}
	@PostMapping(value="delete/{id}/room")
	public boolean deleteRoomById(@PathVariable int id) {
		return roomService.deleteRoomById(id);
	}
	@PostMapping(value="/id/{id}/room")
	public MeetingRoom getRoomById(@PathVariable int id) {
		return roomService.findRoomById(id);
	}
}
