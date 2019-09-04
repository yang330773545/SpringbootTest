package com.vinzor.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SessionController {

	@Autowired
	FindByIndexNameSessionRepository<? extends Session> sessionRepository;
	@GetMapping("/session")
	public String session(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("name", "yang");
		return "设置了session在redis中查看";
	}
	
	
	//通过 user name 获取 session
	@GetMapping("findsession")
	public Map getSessionByname(String name) {
		Map<String, ? extends Session> sessionsMap= sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, name);
	    return sessionsMap;
	}
}
