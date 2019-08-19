package com.vinzor.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vinzor.po.Teacher;

public interface TeacherRepository extends ElasticsearchRepository<Teacher, Long>{

}
