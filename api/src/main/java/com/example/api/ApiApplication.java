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
				
				// thêm dữ liệu để demo, test
				// demo user
				createUserDataDemo(40);
				
	    	}
	    }
	    
	    private void createUserDataDemo(int number) {
	    	for(int i = 1; i <= number; i++) {
	    		Users user = new Users();
		    	user.setEmail("user" + String.valueOf(i) +"@gmail.com");
		    	user.setFullname("Nguyễn Văn An" + String.valueOf(i) );
		    	user.setPassword("12345");
		    	if(i % 2 == 0)
		    		user.setGender(Gender.MALE);
		    	else
		    		user.setGender(Gender.FEMALE);
				usersService.save(user);
	    	}
	    }
	}


}
