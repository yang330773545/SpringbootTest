package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.Participant;
import com.vinzor.mysql.service.ParticpantService;

@RestController
public class ParticipantController {

	@Autowired
	private ParticpantService particpantService;
	
	@GetMapping(value="/getallparticpant")
	public List<Participant> getAllPart(){
		return particpantService.findAllParticipant();
	}
	@GetMapping(value="/delete/{id}/particpant")
	public boolean deletePartbyId( @PathVariable int id) {
		return particpantService.deleteParticipantById(id);	
	}
	@PostMapping(value="/addparticpant")
	public boolean addPart(@RequestBody String json) {
		return particpantService.addParticipant(json);
	}
	@PostMapping(value="/updateparticpant")
	public boolean updateParticpant(@RequestBody String json) {
		return particpantService.updateParticipant(json);
	}
}
