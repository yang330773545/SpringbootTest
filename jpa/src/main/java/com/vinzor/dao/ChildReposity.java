package com.vinzor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinzor.entity.Child;

public interface ChildReposity extends JpaRepository<Child, Long> {

	Child findByChildId(Long childId);
}
