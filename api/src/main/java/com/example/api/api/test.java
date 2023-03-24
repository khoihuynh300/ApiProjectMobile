package com.example.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.Users;
import com.example.api.repository.UsersRepository;
import com.example.api.service.IUsersService;

@RestController
@RequestMapping("api")
public class test {
	
	@Autowired
	IUsersService userService;
	
	@RequestMapping("test")
	public String test1() {
//		System.err.println("this is test");
		
		Users user = new Users();
		user.setFullname("test2");
		userService.save(user);
		return "test2";
	}
}
