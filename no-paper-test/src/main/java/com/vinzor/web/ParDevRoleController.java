package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.ParDevRole;
import com.vinzor.mysql.service.ParDevRoleService;

@RestController
public class ParDevRoleController {

	@Autowired
	ParDevRoleService parDevRoleService;
	
	@GetMapping(value="/getdevrole")
	public List<ParDevRole> findRoleByMeetingId(int meetingId){
		return parDevRoleService.finDevRolesByMeetingId(meetingId);
	}
	@GetMapping(value="/delete/{id}/devrole")
	public boolean deleteRoleById(@PathVariable int id) {
		return parDevRoleService.deleteDevRoles(id);
	}
	@PostMapping(value="/insertdevrole")
	public boolean insertRole(@RequestBody String json) {
		return parDevRoleService.addDevRoles(json);
	}
	@PostMapping(value="/updatedevrole")
	public boolean updateDevRole(@RequestBody String json) {
		return parDevRoleService.updateDevRoles(json);
	}
	
}
