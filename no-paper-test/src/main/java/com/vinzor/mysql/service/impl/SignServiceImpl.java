package com.vinzor.mysql.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinzor.mysql.entity.ParDevRole;
import com.vinzor.mysql.mapper.ParDevRoleMapper;
import com.vinzor.mysql.service.SignService;

@Service
public class SignServiceImpl implements SignService{

	@Autowired
	private ParDevRoleMapper parDevroleMapper;
	
	@Override
	public Map<String, Integer> getSignByMeeting(int id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new LinkedHashMap<>();
		List<ParDevRole> aList=parDevroleMapper.finDevRolesByMeetingId(id);
		int sign=0,notsign=0;
		for(int i=0;i<aList.size();i++) {
			if(aList.get(i).getSign()==1) notsign++;
			if(aList.get(i).getSign()==2) sign++;
		}
		map.put("sign", sign);
		map.put("notsign", notsign);
		map.put("all", sign+notsign);
		return map;
	}

	@Override
	public boolean signWithMeetingAndParticipant(int meetingId, int participantId) {
		// TODO Auto-generated method stub
		int sign=2;
		return parDevroleMapper.UpdateSign(sign, meetingId, participantId)==1 ? true : false;
	}

	
}
