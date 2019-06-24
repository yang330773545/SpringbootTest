package com.vinzor.mysql.service;

import java.time.LocalDateTime;
import java.util.List;

import com.vinzor.mysql.entity.Meeting;

public interface MeetingService {

	List<Meeting> getAllMeetings();
	List<Meeting> getMeetingByRoomId(int meetingRoomId);
	Meeting getMeetingById(int id);
	boolean addMeeting(String json);
	boolean deleteMeetingById(int id);
	boolean updateMeetingSign(int signBackId,int signLogoId,int id);
	boolean updateMeetingProjection(int projectionBackId,int projectionLogoId,int id);
	boolean updateMeetingTable(int tableBackId,int id);
	boolean updateMeetingTheme(int theme,int id);
	boolean updateMeetingScheme(String scheme,int id);
	boolean updateMeeting(LocalDateTime startTime,LocalDateTime endTime,String appointmentPersion,int wayOfSign,int confidentiality,int id);
}
