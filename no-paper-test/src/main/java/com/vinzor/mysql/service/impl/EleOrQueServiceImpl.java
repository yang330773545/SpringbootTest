package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.ElectionOrQuestion;
import com.vinzor.mysql.mapper.ElectionOrQuestionMapper;
import com.vinzor.mysql.service.EleOrQueService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EleOrQueServiceImpl implements EleOrQueService{

	@Autowired
	private ElectionOrQuestionMapper electionOrQuestionMapper;
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public List<ElectionOrQuestion> findEleByMeetingId(int meetingId) {
		// TODO Auto-generated method stub
		log.info("调用了根据会议id查找选举方法 meetingid"+meetingId);
		return electionOrQuestionMapper.findEleByMeetingId(meetingId);
	}

	@Override
	public List<ElectionOrQuestion> findQueByMeetingId(int meetingId) {
		// TODO Auto-generated method stub
		log.info("调用了根据会议id查找问卷方法 meetingid"+meetingId);
		return electionOrQuestionMapper.findQueByMeetingId(meetingId);
	}

	@Override
	public boolean addEleOrQue(String json) {
		// TODO Auto-generated method stub
		try {
			ElectionOrQuestion eoq= mapper.readValue(json, ElectionOrQuestion.class);		
            return electionOrQuestionMapper.addEleOrQue(eoq.getMeetingId(), eoq.getType(), eoq.getElectionContent(), eoq.getOption1(), eoq.getOption2(), eoq.getOption3(), eoq.getOption4(), eoq.getOption5(), eoq.getKind(),eoq.getRegister())==1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加选举或问卷json出错 json："+json+"错误信息"+e.getMessage());
			return false;
		}		
	}

	@Override
	public boolean deleteEleOrQueById(int id) {
		// TODO Auto-generated method stub
		log.info("调用了根据id删除问卷或者选举方法 id"+id);
		return electionOrQuestionMapper.deleteEleOrQueById(id)==1;
	}

	@Override
	public boolean updateEleOrQueById(String json) {
		// TODO Auto-generated method stub
		try {
			ElectionOrQuestion eoq= mapper.readValue(json, ElectionOrQuestion.class);
			return electionOrQuestionMapper.updateEleOrQueById(eoq.getElectionContent(),  eoq.getOption1(), eoq.getOption2(), eoq.getOption3(), eoq.getOption4(), eoq.getOption5(), eoq.getKind(), eoq.getId(),eoq.getRegister())==1;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("更新选举或问卷json出错 json："+json+"错误信息"+e.getMessage());
			return false;
		}		
	}

	@Override
	public boolean updateEleOrQueStatusById(int status, int id) {
		// TODO Auto-generated method stub
		log.info("调用了根据id修改问卷或者选举状态方法 id"+id+"status"+status);
		return electionOrQuestionMapper.updateEleOrQueStatusById(status, id)==1;
	}

}
