package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.Meeting;
import com.vinzor.mysql.mapper.MeetingMapper;
import com.vinzor.mysql.service.MeetingService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeetingServiceImpl implements MeetingService{

	@Autowired
	private MeetingMapper meetingMapper;
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public List<Meeting> getAllMeetings() {
		// TODO Auto-generated method stub
		return meetingMapper.getAllMeetings();
	}

	@Override
	public List<Meeting> getMeetingByRoomId(int meetingRoomId) {
		// TODO Auto-generated method stub
		return meetingMapper.getMeetingByRoomId(meetingRoomId);
	}

	@Override
	public boolean addMeeting(String json) {
		// TODO Auto-generated method stub
		Meeting meeting;
		try {
			meeting = mapper.readValue(json, Meeting.class);
			if(meeting.getStartTime().isAfter(meeting.getEndTime())) return false;
			List<Meeting> aList=this.getMeetingByRoomId(meeting.getMeetingRoomId());
			for(int i=0;i<aList.size();i++) {
				if(meeting.getStartTime().isAfter(aList.get(i).getStartTime()) && meeting.getStartTime().isBefore(aList.get(i).getEndTime())) return false;
				if(meeting.getEndTime().isAfter(aList.get(i).getStartTime()) && meeting.getEndTime().isBefore(aList.get(i).getEndTime())) return false;
			}		
			boolean bool= meetingMapper.addMeeting(meeting)==1 ?  true : false ;
			return bool;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加会议的json解析出错 json"+json);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteMeetingById(int id) {
		// TODO Auto-generated method stub
		boolean bool=meetingMapper.deleteMeetingById(id)==1 ? true : false ;
		return bool;
	}

	@Override
	public boolean updateMeetingSign(int signBackId, int signLogoId, int id) {
		// TODO Auto-generated method stub
		boolean bool=meetingMapper.updateMeetingSign(signBackId, signLogoId, id)==1 ? true : false ;
		return bool;
	}

	@Override
	public boolean updateMeetingProjection(int projectionBackId, int projectionLogoId, int id) {
		// TODO Auto-generated method stub
		boolean bool=meetingMapper.updateMeetingProjection(projectionBackId, projectionLogoId, id)==1 ? true : false ;
		return bool;
	}

	@Override
	public boolean updateMeetingTable(int tableBackId, int id) {
		// TODO Auto-generated method stub
		boolean bool=meetingMapper.updateMeetingTable(tableBackId, id)==1 ? true : false ;
		return bool;
	}

	@Override
	public boolean updateMeetingTheme(int theme, int id) {
		// TODO Auto-generated method stub
		boolean bool=meetingMapper.updateMeetingTheme(theme, id)==1 ? true : false ;
		return bool;
	}

	@Override
	public boolean updateMeetingScheme(String scheme, int id) {
		// TODO Auto-generated method stub
		boolean bool=meetingMapper.updateMeetingScheme(scheme, id)==1 ? true : false ;
		return bool;
	}

	@Override
	public boolean updateMeeting(LocalDateTime startTime, LocalDateTime endTime, String appointmentPersion,
			int wayOfSign, int confidentiality,int id) {
		// TODO Auto-generated method stub
		Meeting meeting=this.getMeetingById(id);
		if(meeting==null) return false;
		List<Meeting> aList=this.getMeetingByRoomId(meeting.getMeetingRoomId());
		//这里懒得改了用迭代器吧
		Iterator<Meeting> aIterator=aList.iterator();
		while(aIterator.hasNext()) {
			if(aIterator.next().getId()==meeting.getId()) {
				continue;
			}
			if(meeting.getStartTime().isAfter(aIterator.next().getStartTime()) && meeting.getStartTime().isBefore(aIterator.next().getEndTime())) return false;
			if(meeting.getEndTime().isAfter(aIterator.next().getStartTime()) && meeting.getEndTime().isBefore(aIterator.next().getEndTime())) return false;			
		}
		boolean bool=meetingMapper.updateMeeting(startTime, endTime, appointmentPersion, wayOfSign, confidentiality,id)==1 ? true : false ;
		return bool;
	}

	@Override
	public Meeting getMeetingById(int id) {
		// TODO Auto-generated method stub
		return meetingMapper.getMeetingById(id);
	}

}
