package com.vinzor.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.EleQueVote;
import com.vinzor.mysql.service.EleQueVoteService;

@RestController
public class EleQueVoteController {
	
	@Autowired
	private EleQueVoteService eleQueVoteService;

	@GetMapping(value="/elequeid/{eleQueId}/participantid/{participantId}/elequevote")
	public EleQueVote findELeQueVoteByEleQueIdAndParticipantId(@PathVariable int eleQueId,@PathVariable int participantId) {
		return eleQueVoteService.findELeQueVoteByEleQueIdAndParticipantId(eleQueId, participantId);
	}
	
	@PostMapping(value="/addelequevote")
	public boolean addEleQueVote(@RequestBody String json) {
		return eleQueVoteService.addEleQueVote(json);
	}
	
	@GetMapping("/elequeid/{eleQueId}/participanteLequevote")
	public Map<String, Integer> findParticipantELeQueVote(@PathVariable int eleQueId){
		return eleQueVoteService.findParticipantELeQueVote(eleQueId);
	}
	
	@GetMapping("/elequeid/{eleQueId}/participantname")
	public Map<String, List<String>> findParticipant(@PathVariable int eleQueId){
		return eleQueVoteService.findParticipant(eleQueId);
	}
	
}
