package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.ElectionOrQuestion;

public interface EleOrQueService {

	List<ElectionOrQuestion> findEleByMeetingId(int meetingId);
	
	List<ElectionOrQuestion> findQueByMeetingId(int meetingId);
	
	boolean addEleOrQue(String json);
	
	boolean deleteEleOrQueById(int id);
	
    boolean updateEleOrQueById(String json);
    
    boolean updateEleOrQueStatusById(int status,int id);
    
	
}
