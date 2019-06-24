package com.vinzor.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.service.SignService;

@RestController
public class SignController {

	@Autowired
	private SignService signService;
	@GetMapping(value="/use/{meetingId}/getsign")
	public Map<String, Integer> getSignByMeeting(@PathVariable int meetingId) {
		return signService.getSignByMeeting(meetingId);
	}
	@GetMapping(value="signbymeetingandpart")
	public boolean signWithMeetingAndParticipant(int meetingId, int participantId) {
		return signService.signWithMeetingAndParticipant(meetingId, participantId);
	}
}
