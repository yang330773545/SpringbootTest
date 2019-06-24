package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.ParDevRole;
import com.vinzor.mysql.mapper.ParDevRoleMapper;
import com.vinzor.mysql.service.ParDevRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParDevRoleServiceImpl implements ParDevRoleService{

	@Autowired
	private ParDevRoleMapper parDevRoleMapper;
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public List<ParDevRole> finDevRolesByMeetingId(int meetingId) {
		// TODO Auto-generated method stub
		
		return parDevRoleMapper.finDevRolesByMeetingId(meetingId);
	}

	@Override
	public boolean updateDevRoles(String json) {
		// TODO Auto-generated method stub
		try {
			ParDevRole role= mapper.readValue(json, ParDevRole.class);
			if(parDevRoleMapper.updateDevRoles(role)==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("更新参会人设备绑定关系 json出错：  "+json);
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addDevRoles(String json) {
		// TODO Auto-generated method stub
		try {
			ParDevRole role= mapper.readValue(json, ParDevRole.class);
			if(parDevRoleMapper.addDevRoles(role)==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加参会人设备绑定关系 json出错：  "+json);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDevRoles(int id) {
		// TODO Auto-generated method stub
		if(parDevRoleMapper.deleteDevRoles(id)==1) return true;
		return false;
	}

}
