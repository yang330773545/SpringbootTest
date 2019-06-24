package com.vinzor.mysql.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.Announcement;
import com.vinzor.mysql.mapper.AnnouncementMapper;
import com.vinzor.mysql.service.AnnouncementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService{

	@Autowired
	private AnnouncementMapper announcementMapper;
	private ObjectMapper mapper=new ObjectMapper();
	@Override
	public List<Announcement> findAnnouncementByMeetingId(int meetingId) {
		// TODO Auto-generated method stub
		return announcementMapper.findAnnouncementByMeetingId(meetingId);
	}
	
	@Override
	public boolean addAnnouncement(String json) {
		// TODO Auto-generated method stub
		try {
			Announcement anno=mapper.readValue(json, Announcement.class);
			if(announcementMapper.addAnnouncement(anno.getMeetingId(), anno.getAnnoTitle(), anno.getAnnoContent(), anno.getLogoFileId(), anno.getBackgroundFileId())==1)return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("添加公告的json解析出错 json："+json+"错误信息"+e.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public boolean updateAnnouncementById(String json) {
		// TODO Auto-generated method stub
		try {
			Announcement anno=mapper.readValue(json, Announcement.class);
			if(announcementMapper.findAnnouncementById(anno.getId())==null) {
				log.error("修改公告的公告id无对应公告 id为"+anno.getId());
				return false;
			}
			if(announcementMapper.updateAnnouncementById(anno.getId(), anno.getAnnoTitle(), anno.getAnnoContent(), anno.getLogoFileId(), anno.getBackgroundFileId())==1) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("修改公告的json解析出错 json："+json+"错误信息"+e.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public boolean deleteAnnouncementById(int id) {
		// TODO Auto-generated method stub
		if(announcementMapper.findAnnouncementById(id)==null) {
			log.error("删除公告时公告id无对应内容 id"+id);
			return false;
		}
		if(announcementMapper.deleteAnnouncementById(id)==1) return true;
		return false;
	}


	@Override
	public Announcement findAnnouncementById(int id) {
		// TODO Auto-generated method stub
		return announcementMapper.findAnnouncementById(id);
	}

}
