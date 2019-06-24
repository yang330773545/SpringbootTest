package com.vinzor.mysql.service;

import java.util.List;

import com.vinzor.mysql.entity.Announcement;

public interface AnnouncementService {

	List<Announcement> findAnnouncementByMeetingId(int meetingId);
	boolean addAnnouncement(String json);
	boolean updateAnnouncementById(String json);
	boolean deleteAnnouncementById(int id);
	Announcement findAnnouncementById(int id);
}
