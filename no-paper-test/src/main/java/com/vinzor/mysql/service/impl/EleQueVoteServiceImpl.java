package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.EleQueVote;
import com.vinzor.mysql.mapper.EleQueVoteMapper;
import com.vinzor.mysql.mapper.ParticipantMapper;
import com.vinzor.mysql.service.EleQueVoteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EleQueVoteServiceImpl implements EleQueVoteService{

	@Autowired
	private EleQueVoteMapper eleQueVoteMapper;
	private ObjectMapper mapper=new ObjectMapper();
	@Autowired
	private ParticipantMapper participantMapper;
	
	@Override
	public EleQueVote findELeQueVoteByEleQueIdAndParticipantId(int eleQueId, int participantId) {
		// TODO Auto-generated method stub
		log.info("调用了根据投票或选举id和参会人id查找参会人投票信息方法"+eleQueId+"..."+participantId);
		return eleQueVoteMapper.findELeQueVoteByEleQueIdAndParticipantId(eleQueId, participantId);
	}

	@Override
	public boolean addEleQueVote(String json) {
		// TODO Auto-generated method stub
		log.info("调用了添加投票或选举信息方法"+json);
		try {
			EleQueVote eleQueVote=mapper.readValue(json, EleQueVote.class);
			if(eleQueVoteMapper.addEleQueVote(eleQueVote)==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加投票或选举json出错");
			return false;
		}
		return false;
	}

	@Override
	public Map<String, Integer> findParticipantELeQueVote(int eleQueId) {
		// TODO Auto-generated method stub
		List<EleQueVote> aList=eleQueVoteMapper.findELeQueVoteByEleQueId(eleQueId);
		Map<String, Integer> map=new LinkedHashMap<>();
		int choose1 = 0,choose2=0,choose3=0,choose4=0,choose5=0;
		for(int i=0;i<aList.size();i++) {
			if(aList.get(i).getChoose1()==1) choose1++;
			if(aList.get(i).getChoose2()==1) choose2++;
			if(aList.get(i).getChoose3()==1) choose3++;
			if(aList.get(i).getChoose4()==1) choose4++;
			if(aList.get(i).getChoose5()==1) choose5++;
		}
		map.put("choose1", choose1);
		map.put("choose2", choose2);
		map.put("choose3", choose3);
		map.put("choose4", choose4);
		map.put("choose5", choose5);
		return map;
	}

	@Override
	public Map<String, List<String>> findParticipant(int eleQueId) {
		// TODO Auto-generated method stub
		List<EleQueVote> aList=eleQueVoteMapper.findELeQueVoteByEleQueId(eleQueId);
		Map<String, List<String>> map=new LinkedHashMap<String, List<String>>();
		List<String> c1List= new ArrayList<>();
		List<String> c2List= new ArrayList<>();
		List<String> c3List= new ArrayList<>();
		List<String> c4List= new ArrayList<>();
		List<String> c5List= new ArrayList<>();
		for(int i=0;i<aList.size();i++) {
			if(aList.get(i).getChoose1()==1) c1List.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
			if(aList.get(i).getChoose2()==1) c2List.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
			if(aList.get(i).getChoose3()==1) c3List.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
			if(aList.get(i).getChoose4()==1) c4List.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
			if(aList.get(i).getChoose5()==1) c5List.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
		}
		map.put("choose1", c1List);
		map.put("choose2", c2List);
		map.put("choose3", c3List);
		map.put("choose4", c4List);
		map.put("choose5", c5List);
		return map;
	}

}
