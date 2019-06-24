package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.Participant;
import com.vinzor.mysql.mapper.ParticipantMapper;
import com.vinzor.mysql.service.ParticpantService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParticpantServiceimpl implements ParticpantService{

	@Autowired
	private ParticipantMapper participantMapper;
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public List<Participant> findAllParticipant() {
		// TODO Auto-generated method stub
		return participantMapper.findAllParticipant();
	}

	@Override
	public boolean deleteParticipantById(int id) {
		// TODO Auto-generated method stub
		if(participantMapper.deleteParticipantById(id)==1) return true;
		return false;
	}

	@Override
	public boolean addParticipant(String json) {
		// TODO Auto-generated method stub
		try {
			Participant part=mapper.readValue(json, Participant.class);
			if(participantMapper.addParticipant(part.getName(), part.getSex(), part.getPosition(), part.getPhoneNumber())==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加参会人json解析出错 json："+json);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateParticipant(String json) {
		// TODO Auto-generated method stub
		try {
			Participant part=mapper.readValue(json, Participant.class);
			if(participantMapper.updateParticipant(part.getName(), part.getSex(), part.getPosition(), part.getPhoneNumber(), part.getId())==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("更新参会人信息json解析出错 json："+json);
			e.printStackTrace();
		}
		return false;
	}

}
