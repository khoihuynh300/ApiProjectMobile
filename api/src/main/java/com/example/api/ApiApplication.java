package com.example.api;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.example.api.entity.Users;
import com.example.api.entity.Users.Gender;
import com.example.api.model.InnModel;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.impl.InnServiceImpl;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) throws IOException{
		SpringApplication.run(ApiApplication.class, args);
	}
	
	@Configuration
	public class DataInitializer {
		@Autowired 
		IUsersService usersService;
		
		@Autowired IInnService innService;

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
				
//				System.err.println(user.getUserId());
				
				InnModel newInnModel = new InnModel();
				newInnModel.setAddress("123 Linh Trung, Thủ Đức, TPHCM");
				newInnModel.setDescribe("Phòng trọ an ninh, cách trường ĐHSPKT 2km");
				newInnModel.setPhoneNumber("0123456789");
				newInnModel.setPrice((double) 2000000);
				newInnModel.setPriceELec((double) 2000);
				newInnModel.setPriceWater((double) 2000);
				newInnModel.setSize(2);
				newInnModel.setProposedId(user.getUserId());
				
				List<String> imageArr = new ArrayList<>() ;
				imageArr.add("Tro15.png");
				imageArr.add("Tro16.png");
				imageArr.add("Tro17.png");
				
				innService.recommendInn(newInnModel, imageArr);
	    	}
	    }
	}


}
