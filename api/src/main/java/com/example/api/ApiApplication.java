package com.example.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.example.api.entity.Inn;
import com.example.api.entity.Message;
import com.example.api.entity.Question;
import com.example.api.entity.Users;
import com.example.api.entity.Users.Gender;
import com.example.api.model.InnModel;
import com.example.api.service.IInnService;
import com.example.api.service.IQuestionService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;

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
		
		@Autowired IQuestionService iQuestionService;

		@Autowired MessageService messageService;

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
		    	user.setGender(Gender.MALE);
				usersService.save(user);
				
				// thêm dữ liệu để demo, test
				// demo user
				createDataDemo(25);
				
				
				
	    	}
	    }
	    
	    private void createDataDemo(int number) {
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
				
				// Thêm Inn
				InnModel newInnModel = new InnModel();
				newInnModel.setAddress("123 Linh Trung, Thủ Đức, TPHCM");
				newInnModel.setDescribe("Phòng trọ an ninh, cách trường ĐHSPKT 2km");
				newInnModel.setPhoneNumber("0123456789");
				newInnModel.setPrice((double) 2000000);
				newInnModel.setPriceELec((double) 2000);
				newInnModel.setPriceWater((double) 2000);
				newInnModel.setSize(2);
				newInnModel.setProposedId(user.getUserId()/5 + 2);
				
				
				List<String> imageArr = new ArrayList<>() ;
				imageArr.add("Tro15.png");
				imageArr.add("Tro16.png");
				imageArr.add("Tro17.png");
				
				innService.recommendInn(newInnModel, imageArr);
				
				if(i < 15) {
					Users userAdmin = usersService.findById((long) 1).get();
					Inn inn = innService.findById((long) i).get();
					inn.setConfirmedById(userAdmin);
					inn.setIsConfirmed(true);
					
					innService.save(inn);
				}
				
				// Thêm Question
				Question question = new Question();
				question.setAskedId(user);
				question.setTitle("Title");
				iQuestionService.save(question);
				
				Message ask = new Message();
				ask.setMessage("Câu hỏi");
				ask.setQuestionId(question);
				ask.setUserId(usersService.findById((long) i).get());
				
				messageService.save(ask);
				
				if(i < 15) {
					Message answer = new Message();
					answer.setMessage("Câu trả lời");
					answer.setQuestionId(question);
					answer.setUserId(usersService.findById((long) 1).get());
					messageService.save(answer);
				}
				
				
	    	}
	    }
	}


}
