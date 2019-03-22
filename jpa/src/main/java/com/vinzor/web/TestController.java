package com.vinzor.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinzor.entity.Dog;
import com.vinzor.entity.User;
import com.vinzor.entity.UserIsDog;
import com.vinzor.service.DogService;
import com.vinzor.service.UserService;

@RestController
public class TestController {

	@Autowired
	UserService userService;
	@Autowired
	DogService dogService;
	@RequestMapping(value="jpa")
	public List<User> look() {
		User user=new User();
		user.setId(1);
		user.setName("name");
		user.setPass("pass");
		userService.add(user);
		return userService.findall();
	}
	@RequestMapping(value="jpaa")
	public List<Dog> lookdog(){
		Dog dog=new Dog();
		dog.setId(1);
		dog.setDogAge(10);
		dog.setDogName("gouzi");
		dogService.add(dog);
		return dogService.findAll();
	}
	@RequestMapping(value="jpaaa")
	public List<UserIsDog> lookuserisdog(){
		return userService.findUserIsDog();
	}
}
