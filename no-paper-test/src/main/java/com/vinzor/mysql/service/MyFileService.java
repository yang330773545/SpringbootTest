package com.vinzor.mysql.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.vinzor.mysql.entity.MyFile;

public interface MyFileService {

	MyFile findFileById(int id);
	
	List<MyFile> findAllFile();
	
	boolean addFile(MultipartFile file,int type);
	
	boolean deleteFile(int id);	
	
	List<MyFile> findFileByType(int type);
	
	List<MyFile> findMeetingFileByType(int meetingId);
	
	List<MyFile> findSharedFileByType(int meetingId);
	
	boolean addMeetingFile(HttpServletRequest request);
	
	boolean addSharedFile(HttpServletRequest request);
}
