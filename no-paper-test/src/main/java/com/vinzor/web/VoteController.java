package com.vinzor.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.Vote;
import com.vinzor.mysql.service.VoteService;

@RestController
public class VoteController {

	@Autowired
	private VoteService voteService;
	
	@GetMapping(value="/getallvote")
	public List<Vote> findAllVote(){
		return voteService.findAllVote();
	}
	
	@GetMapping(value="/use/{meetingId}/getvote")
	public List<Vote> findVoteByMeeting(@PathVariable int meetingId){
		return voteService.findVoteByMeeting(meetingId);
	}
	
	@PostMapping(value="/addvote")
	public boolean addVite(@RequestBody String json) {
		return voteService.addVote(json);
	}
	
	@PostMapping(value="/updatevote")
	public boolean updateVoteById(@RequestBody String json) {
		return voteService.updateVoteById(json);
	}
	
	@PostMapping(value="/updatevotestatus")
	public boolean updateVoteStatus(@RequestBody String json) {
		return voteService.updateVoteStatus(json);
	}
	
	@PostMapping(value="/use/{id}/delevote")
	public boolean deleteVoteById(@PathVariable int id) {
		return voteService.deleteVoteById(id);
	}
}
