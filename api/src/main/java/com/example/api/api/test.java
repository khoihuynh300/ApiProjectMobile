package com.example.api.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("test")
	public String test1() {
		System.err.println("this is test");

		Users user = new Users();
		user.setFullname("test2");
		user.setEmail("test@gmail.com");
		userService.save(user);
		return "test2";
	}
	
	 @GetMapping("/test2")
	    public Map<String, Object> getResponse() {
	        Users user = new Users();
	        user.setFullname("test2");
			user.setEmail("test@gmail.com");
			
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "m");
	        response.put("user", user);
	        return response;
	    }
}
