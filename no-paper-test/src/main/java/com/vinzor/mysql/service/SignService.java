package com.vinzor.mysql.service;

import java.util.Map;

public interface SignService {
 
	Map<String, Integer> getSignByMeeting(int id);
	
	boolean signWithMeetingAndParticipant(int meetingId,int participantId);
	
}
