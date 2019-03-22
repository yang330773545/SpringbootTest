package com.vinzor.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinzor.entity.User;
import com.vinzor.entity.UserIsDog;

public interface UserRepository extends JpaRepository<User, Long> {
	
	  @Query(value = " SELECT new com.vinzor.entity.UserIsDog(d,u) FROM User u, Dog d WHERE u.id = d.id ")
	  public List<UserIsDog> findUserIsDog();
}
