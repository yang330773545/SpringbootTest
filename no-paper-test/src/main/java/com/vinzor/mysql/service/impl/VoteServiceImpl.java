package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.Vote;
import com.vinzor.mysql.mapper.VoteMapper;
import com.vinzor.mysql.service.VoteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VoteServiceImpl implements VoteService{

	@Autowired
	private VoteMapper voteMapper;
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public List<Vote> findAllVote() {
		// TODO Auto-generated method stub
		log.info("调用了查询所有投票的接口");
		return voteMapper.findAllVote();
	}

	@Override
	public List<Vote> findVoteByMeeting(int meetingId) {
		// TODO Auto-generated method stub
		log.info("调用了通过会议id查询投票的接口 会议id："+ meetingId);
		return voteMapper.findVoteByMeeting(meetingId);
	}

	@Override
	public boolean addVote(String json) {
		// TODO Auto-generated method stub
		log.info("调用添加投票接口 json为"+json);
		try {
			Vote vote=mapper.readValue(json, Vote.class);
			boolean bool=voteMapper.addVote(vote)==1 ? true : false ;
			return bool;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加投票json解析出错");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateVoteById(String json) {
		// TODO Auto-generated method stub
		log.info("调用修改投票接口 json为"+json);
		try {
			Vote vote=mapper.readValue(json, Vote.class);
			boolean bool=voteMapper.updateVoteById(vote)==1 ? true : false ;
			return bool;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("修改投票json解析出错");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateVoteStatus(String json) {
		// TODO Auto-generated method stub
		log.info("调修改会议状态接口 json为"+json);
		try {
			Vote vote=mapper.readValue(json, Vote.class);
			boolean bool=voteMapper.updateVoteStatus(vote)==1 ? true : false ;
			return bool;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("修改投票状态json解析出错");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteVoteById(int id) {
		// TODO Auto-generated method stub
		log.info("调用删除投票接口 id:"+id);
		return voteMapper.deleteVoteById(id)==1 ? true : false;
	}

}
