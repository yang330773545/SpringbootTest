package com.vinzor.web;

import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
		// QueryBuilders 构建查询构造器 SortBuilders构建排序构造器 使用NativeSearchQueryBuilder来组合得到SearchQuery 详细用法见隔壁
		// SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("spring boot OR 书籍")).build();
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
