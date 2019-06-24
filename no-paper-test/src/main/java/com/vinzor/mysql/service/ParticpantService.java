package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.Participant;

public interface ParticpantService {

	List<Participant> findAllParticipant();
	boolean deleteParticipantById(int id);
	boolean addParticipant(String json);
	boolean updateParticipant(String json);
}
