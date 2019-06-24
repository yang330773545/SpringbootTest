package com.vinzor.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.Meeting;
import com.vinzor.mysql.service.MeetingService;

@RestController
public class MeetingController {

	@Autowired
	private MeetingService meetingService;
	
	@RequestMapping(value="/getallmeeting",method=RequestMethod.GET)
	public List<Meeting> getAllMeeting(){
		return meetingService.getAllMeetings();
	}
	
	@GetMapping(value="/use/{roomId}/findmeeting")
	public List<Meeting> getMeetingByRoomId(@PathVariable int roomId){
		return meetingService.getMeetingByRoomId(roomId);
	}

	@GetMapping(value="use/{id}/deletemeeting")
	public boolean deleteMeetingById(@PathVariable int id) {
		return meetingService.deleteMeetingById(id);
	}
	
	@PostMapping(value="/addmeeting")
	public boolean addMeeting(@RequestBody String json) {
		return meetingService.addMeeting(json);
	}
			
	@PostMapping(value="/updatemeeting/theme")
	public boolean updateMeetingTheme(int theme, int id) {
		return meetingService.updateMeetingTheme(theme, id);
	}
	
	@PostMapping(value="/updatemeeting/scheme")
	public boolean updateMeetingScheme(String scheme, int id) {
		return meetingService.updateMeetingScheme(scheme, id);
	}
	
	@PostMapping(value="/updatemeeting")
	public boolean updateMeeting(LocalDateTime startTime, LocalDateTime endTime, String appointmentPersion,
			int wayOfSign, int confidentiality,int id) {
		return meetingService.updateMeeting(startTime, endTime, appointmentPersion, wayOfSign, confidentiality, id);
	}
}
