package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.ParDevRole;

public interface ParDevRoleService {

	List<ParDevRole> finDevRolesByMeetingId(int meetingId);
	boolean updateDevRoles(String json);
	boolean addDevRoles(String json);
	boolean deleteDevRoles(int id);
}
