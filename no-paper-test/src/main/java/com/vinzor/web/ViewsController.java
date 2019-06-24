package com.vinzor.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewsController {

	@Autowired
	private UserController userController;
	@Autowired
	private RoomController roomController; 
	@Resource
	private ParticipantController participantController;
	
	/**
	 *   秘书管理的view视图
	 */
	@GetMapping("/usermanagement")
	public String getUserManagementView(Model model) {		
		model.addAttribute("user_list", userController.selectAllUser());
		return "usermanagement";
	}
	@GetMapping("/useradd")
	public String getUserAddView() {		
		return "adduser";
	}
	@GetMapping("/userupdate")
	public String getUserUpdateView(Model model,int id) {
		model.addAttribute("user", userController.selectUserById(id));
		return "updateuser";
	}
	/**
	 *   会议室管理的view视图
	 */
	@GetMapping("/meetingroommanagement")
	public String getMeetingRoomManagementView(Model model) {		
		model.addAttribute("room_list", roomController.getAllRoom());
		return "meetingroommanagement";
	}
	@GetMapping("/meetingroomadd")
	public String getMeetingRoomAddView() {		
		return "meeting_room_add";
	}
	@GetMapping("/meetingroomupdate")
	public String getMeetingRoomUpdateView(Model model,int id) {	
		model.addAttribute("room", roomController.getRoomById(id));
		return "meeting_room_update";
	}
	/**
	 *   设备管理的view视图
	 */
	
	/**
	 *   参会人管理的view视图
	 */
	@GetMapping(value="/participantmanagement")
	public String getParticipantManagementView(Model model) {
		model.addAttribute("participant_list", participantController.getAllPart());
		return "participant_management";
	}

}
