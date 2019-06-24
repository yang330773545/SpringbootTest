package com.vinzor.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.ParticipantVote;
import com.vinzor.mysql.service.ParticipantVoteService;

@RestController
public class ParticipantVoteController {

	@Autowired
	ParticipantVoteService participantVoteService;
	
	@GetMapping(value="/use/{voteId}/getparticipantvote")
	public Map<String, Integer> findParticipantVote(@PathVariable int voteId){
		return participantVoteService.findParticipantVote(voteId);
	}
	@GetMapping(value="/use/{voteId}/getparticipant")
	public Map<String,List<String>> findParticipant(@PathVariable int voteId) {
		return participantVoteService.findParticipant(voteId);
	}
	@GetMapping(value="/voteid/{voteId}/participantid/{participantId}")
	public ParticipantVote findParticipantby(@PathVariable int voteId, @PathVariable int participantId) {
		return participantVoteService.findParticipanVoteByVoteIdAndParticipantId(voteId, participantId);
	}
	@PostMapping(value="/addparticipantvote")
	public boolean addParticipantVote(@RequestBody String json) {
		return participantVoteService.addParticipantVote(json);
	}
}
