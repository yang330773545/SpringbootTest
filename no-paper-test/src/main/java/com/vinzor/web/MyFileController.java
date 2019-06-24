package com.vinzor.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vinzor.mysql.entity.MyFile;
import com.vinzor.mysql.service.MyFileService;

@RestController
public class MyFileController {

	@Autowired
	private MyFileService myFileService;
	
	@GetMapping(value="/getallfile")
	public List<MyFile> findAllFile(){
		return myFileService.findAllFile();
	}
	
	@GetMapping(value="/getfilebytype")
	public List<MyFile> findFileByType(int type){
		return myFileService.findFileByType(type);
	}
	
	@GetMapping(value="/id/{id}/getmyfile")
	public MyFile findMyFileById(@PathVariable int id) {
		return myFileService.findFileById(id);
	}
	
	@PostMapping(value="/id/{id}/deletemyfile")
	public boolean deleteMyFile(@PathVariable int id) {
		return myFileService.deleteFile(id);
	}
	
	@PostMapping(value="/add/type/{type}/file")
	public boolean addMyFile(@RequestParam("file") MultipartFile file,@PathVariable int type) {
		return myFileService.addFile(file,type);
	}
	
	@GetMapping(value="/meetingId/{meetingId}/meetingfile")
	public List<MyFile> findMeetingFileByType(@PathVariable int meetingId){
		return myFileService.findMeetingFileByType(meetingId);
	}
	
	@GetMapping(value="/meetingId/{meetingId}/sharedfile")
	public List<MyFile> findSharedFileByType(@PathVariable int meetingId){
		return myFileService.findSharedFileByType(meetingId);
	}
	
	@PostMapping(value="/addmeetingfile")
	public boolean addMeetingFile(HttpServletRequest request) {
		return myFileService.addMeetingFile(request);
	}
	

	@PostMapping(value="/addsharedfile")
	public boolean addSharedFile(HttpServletRequest request) {
		return myFileService.addSharedFile(request);
	}
}
