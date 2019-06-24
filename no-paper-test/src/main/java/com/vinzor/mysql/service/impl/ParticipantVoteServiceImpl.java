package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.ParticipantVote;
import com.vinzor.mysql.mapper.ParticipantMapper;
import com.vinzor.mysql.mapper.ParticipantVoteMapper;
import com.vinzor.mysql.service.ParticipantVoteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParticipantVoteServiceImpl implements ParticipantVoteService{

	@Autowired
	private ParticipantVoteMapper participantVoteMapper;
	@Autowired
	private ParticipantMapper participantMapper;
	
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public Map<String, Integer> findParticipantVote(int voteId) {
		log.info("调用查找投票结果接口 投票id："+voteId);
		// TODO Auto-generated method stub
		List<ParticipantVote> aList=participantVoteMapper.ParticipantVoteByVoteId(voteId);
		Map<String, Integer> map=new HashMap<>();
		int concur = 0,contra=0,abstain=0;
		for(int i=0;i<aList.size();i++) {
			if(aList.get(i).getVoteOpinion()==1) concur++;
			if(aList.get(i).getVoteOpinion()==2) contra++;
			if(aList.get(i).getVoteOpinion()==3) abstain++;
		}
		map.put("concur", concur);
		map.put("contra", contra);
		map.put("abstain", abstain);
		map.put("sum", aList.size());
		return map;
		
	}

	@Override
	public Map<String,List<String>> findParticipant(int voteId) {
		log.info("调用查找投票结果（记名）接口 投票id："+voteId);
		// TODO Auto-generated method stub
		List<ParticipantVote> aList=participantVoteMapper.ParticipantVoteByVoteId(voteId);
		Map<String,List<String>> map=new HashMap<>();
		List<String> bList=new ArrayList<String>();
		List<String> cList=new ArrayList<String>();
		List<String> dList=new ArrayList<String>();
		for(int i=0;i<aList.size();i++) {
			if(aList.get(i).getVoteOpinion()==1) bList.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
			if(aList.get(i).getVoteOpinion()==2) cList.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
			if(aList.get(i).getVoteOpinion()==3) dList.add(participantMapper.findParticipantById(aList.get(i).getParticipantId()).getName());
		}
		map.put("concur", bList);
		map.put("contra", cList);
		map.put("abstain", dList);
		return map;
	}

	@Override
	public boolean addParticipantVote(String json) {
		log.info("调用添加投票结果 json:"+json);
		// TODO Auto-generated method stub
		try {
			ParticipantVote participantVote=mapper.readValue(json, ParticipantVote.class);
			if(participantVoteMapper.addParticipantVote(participantVote)==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加投票json出错");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ParticipantVote findParticipanVoteByVoteIdAndParticipantId(int voteId, int participantId) {
		// TODO Auto-generated method stub
		return participantVoteMapper.findParticipanVoteByVoteIdAndParticipantId(voteId, participantId);
	}

}
