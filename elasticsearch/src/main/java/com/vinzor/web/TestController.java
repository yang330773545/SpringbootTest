package com.vinzor.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.dao.TeacherRepository;
import com.vinzor.po.Teacher;

@RestController
public class TestController {

	@Autowired
	private TeacherRepository teacherRepository;
	@RequestMapping(value="/add")
	public String addElasticSearch() {
		Teacher teacher=new Teacher();
		teacher.setId(1);
		teacher.setAge(10);
		teacher.setName("狗子");
		teacher.setSchool("垃圾学校");
		teacherRepository.save(teacher);		
		return "应该是这样吧";
	}
	@RequestMapping(value="/update")
	public String updateElasticSearch() {
		Teacher teacher=new Teacher();
		teacher.setId(1);
		teacher.setAge(101);
		teacher.setName("狗子1");
		teacher.setSchool("垃圾学校a");
		teacherRepository.save(teacher);		
		return "去看看改了没有";
	}
	@RequestMapping(value="/select")
	public Teacher selectElasticSearch() {				
		Optional<Teacher> optional=teacherRepository.findById((long) 1);
		Teacher teacher=optional.get();
		return teacher;
	}
	@RequestMapping(value="/delete")
	public String deleteElasticSearch() {
		teacherRepository.deleteById((long) 1);
		return "删除了";
	}
	
}
