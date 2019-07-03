package com.vinzor.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinzor.entity.User;
import com.vinzor.entity.UserIsDog;

//这里是为了方便名称懒得改了
public interface UserRepository extends JpaRepository<User, Long> {
	
	  //一对一关系的实现
	  @Query(value = " SELECT new com.vinzor.entity.UserIsDog(d,u) FROM User u, Dog d WHERE u.id = d.id ")
	  public List<UserIsDog> findUserIsDog();
}
