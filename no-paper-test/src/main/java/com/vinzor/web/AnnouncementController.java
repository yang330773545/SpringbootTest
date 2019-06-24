package com.vinzor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.mysql.entity.Announcement;
import com.vinzor.mysql.service.AnnouncementService;

@RestController
public class AnnouncementController {

	@Autowired
	AnnouncementService announcementService;
	
	@RequestMapping(value="/meetingid/{meetingId}/getannouncement",method=RequestMethod.GET)
	public List<Announcement> findAnnouncementByMeetingId(@PathVariable int meetingId){
		return announcementService.findAnnouncementByMeetingId(meetingId);
	}
	
	@RequestMapping(value="/id/{id}/getannouncement",method=RequestMethod.GET)
	public Announcement findAnnouncementById(@PathVariable int id) {
		return announcementService.findAnnouncementById(id);
	}
	
	@RequestMapping(value="/addAnnouncement",method=RequestMethod.POST)
    public boolean insertAnnouncement(@RequestBody String json) {
		return announcementService.addAnnouncement(json);
	}
	
	@RequestMapping(value="/updateAnnouncement",method=RequestMethod.POST)
    public boolean updateAnnouncement(@RequestBody String json) {
		return announcementService.updateAnnouncementById(json);
	}
	
	@RequestMapping(value="/id/{id}/deleteannouncement",method=RequestMethod.POST)
	public boolean deleteAnnouncementByid(@PathVariable int id) {
		return announcementService.deleteAnnouncementById(id);		
	}
}
