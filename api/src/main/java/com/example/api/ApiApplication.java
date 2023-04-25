package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.example.api.entity.Users;
import com.example.api.entity.Users.Gender;
import com.example.api.service.IUsersService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	
	@Configuration
	public class DataInitializer {
		@Autowired 
		IUsersService usersService;

	    @PostConstruct
	    public void init() {
	    	if(usersService.count() == 0) {	    		
		        System.err.println("init data");
		        // user admin
		    	Users user = new Users();
		    	user.setEmail("admin@gmail.com");
		    	user.setFullname("admin");
		    	user.setPassword("admin");
		    	user.setRole("manager");
		    	user.setActive(true);
				usersService.save(user);
				// user test
				Users user2 = new Users();
		    	user2.setEmail("test@gmail.com");
		    	user2.setFullname("test");
		    	user2.setPassword("test");
		    	user2.setGender(Gender.MALE);
				usersService.save(user);
	    	}
	    }
	}


}
