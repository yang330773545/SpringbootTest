package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.ElectionOrQuestion;
import com.vinzor.mysql.service.EleOrQueService;

@RestController
public class EleOrQueController {

	@Autowired
	private EleOrQueService eleOrQueService;
	
	@GetMapping("/meetingid/{meetingId}/getelection")
	public List<ElectionOrQuestion> getEleByMeetingid(@PathVariable int meetingId){
		return eleOrQueService.findEleByMeetingId(meetingId);
	}
	
	@GetMapping("/meetingid/{meetingId}/getquestion")
	public List<ElectionOrQuestion> getQueByMeetingid(@PathVariable int meetingId){
		return eleOrQueService.findQueByMeetingId(meetingId);
	}
	
	@PostMapping("/addelectionorquestion")
	public boolean addEleOrQue(@RequestBody String json) {
		return eleOrQueService.addEleOrQue(json);
	}
	
	@PostMapping("/id/{id}/deleteeleorque")
	public boolean deleteEleOrQueById(@PathVariable int id) {
		return eleOrQueService.deleteEleOrQueById(id);
	}
	
	@PostMapping("/updateeleorque")
	public boolean updateEleOrQueById(@RequestBody String json) {
		return eleOrQueService.updateEleOrQueById(json);
	}
	@PostMapping("/updateeleorquestatus")
	public boolean updateEleOrQueStatusById(int status, int id) {
		return eleOrQueService.updateEleOrQueStatusById(status, id);
	}
}
