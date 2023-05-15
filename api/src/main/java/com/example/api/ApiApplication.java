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
import com.example.api.utils.images;

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
		    	Users manager = new Users();
		    	manager.setEmail("manager@gmail.com");
		    	manager.setFullname("manager");
		    	manager.setPassword("manager");
		    	manager.setRole("manager");
		    	manager.setActive(true);
		    	manager.setGender(Gender.MALE);
	    		manager.setAvatar(images.getImage("defaultAdmin.png"));
				usersService.save(manager);
				
				// thêm dữ liệu để demo, test
				// demo user
//<<<<<<< HEAD
//				createUserDataDemo(10);
//=======
//				createDataDemo(25);
				
				Users tuvanvien1 = new Users();
		    	tuvanvien1.setEmail("annguyen@gmail.com");
		    	tuvanvien1.setFullname("Nguyễn Văn An");
		    	tuvanvien1.setPassword("12345");
	    		tuvanvien1.setGender(Gender.MALE);
	    		tuvanvien1.setAvatar(images.getImage("boy1.jpg"));
	    		tuvanvien1.setRole("tuvanvien");
				usersService.save(tuvanvien1);
				
				Users tuvanvien2 = new Users();
		    	tuvanvien2.setEmail("tamtran@gmail.com");
		    	tuvanvien2.setFullname("Trần Văn Tâm");
		    	tuvanvien2.setPassword("12345");
	    		tuvanvien2.setGender(Gender.MALE);
	    		tuvanvien2.setAvatar(images.getImage("boy2.jpg"));
	    		tuvanvien2.setRole("tuvanvien");
				usersService.save(tuvanvien2);
				
				Users tuvanvien3 = new Users();
		    	tuvanvien3.setEmail("vietle@gmail.com");
		    	tuvanvien3.setFullname("Lê Văn Việt");
		    	tuvanvien3.setPassword("12345");
	    		tuvanvien3.setGender(Gender.MALE);
	    		tuvanvien3.setAvatar(images.getImage("boy3.jpg"));
	    		tuvanvien3.setRole("tuvanvien");
				usersService.save(tuvanvien3);
				
				Users tuvanvien4 = new Users();
		    	tuvanvien4.setEmail("huytran@gmail.com");
		    	tuvanvien4.setFullname("Trần Văn Huy");
		    	tuvanvien4.setPassword("12345");
	    		tuvanvien4.setGender(Gender.MALE);
	    		tuvanvien4.setAvatar(images.getImage("boy4.jpg"));
	    		tuvanvien4.setRole("tuvanvien");
				usersService.save(tuvanvien4);
				
				Users sinhvien1 = new Users();
		    	sinhvien1.setEmail("minhpham@gmail.com");
		    	sinhvien1.setFullname("Phạm Nhật Minh");
		    	sinhvien1.setPassword("12345");
	    		sinhvien1.setGender(Gender.MALE);
	    		sinhvien1.setAvatar(images.getImage("animal1.jpg"));
				usersService.save(sinhvien1);
				
				Users sinhvien2 = new Users();
		    	sinhvien2.setEmail("thanhthoai@gmail.com");
		    	sinhvien2.setFullname("Từ Thanh Thoại");
		    	sinhvien2.setPassword("12345");
	    		sinhvien2.setGender(Gender.MALE);
	    		sinhvien2.setAvatar(images.getImage("animal9.jpg"));
				usersService.save(sinhvien2);
				
				Users sinhvien3 = new Users();
		    	sinhvien3.setEmail("khoihuynh@gmail.com");
		    	sinhvien3.setFullname("Huỳnh Tấn Khôi");
		    	sinhvien3.setPassword("12345");
	    		sinhvien3.setGender(Gender.MALE);
	    		sinhvien3.setAvatar(images.getImage("animal3.jpg"));
				usersService.save(sinhvien3);
				
				Users sinhvien4 = new Users();
		    	sinhvien4.setEmail("tunguyen@gmail.com");
		    	sinhvien4.setFullname("Nguyễn Như Tú");
		    	sinhvien4.setPassword("12345");
	    		sinhvien4.setGender(Gender.MALE);
	    		sinhvien4.setAvatar(images.getImage("animal4.jpg"));
				usersService.save(sinhvien4);
				
				Users sinhvien5 = new Users();
		    	sinhvien5.setEmail("namtran@gmail.com");
		    	sinhvien5.setFullname("Trần Cảnh Nam");
		    	sinhvien5.setPassword("12345");
	    		sinhvien5.setGender(Gender.MALE);
	    		sinhvien5.setAvatar(images.getImage("animal5.jpg"));
				usersService.save(sinhvien5);
				
				Users sinhvien6 = new Users();
		    	sinhvien6.setEmail("ductien@gmail.com");
		    	sinhvien6.setFullname("Bùi Đức Tiên");
		    	sinhvien6.setPassword("12345");
	    		sinhvien6.setGender(Gender.MALE);
	    		sinhvien6.setAvatar(images.getImage("animal6.jpg"));
				usersService.save(sinhvien6);
				
				Users sinhvien7 = new Users();
		    	sinhvien7.setEmail("congphat@gmail.com");
		    	sinhvien7.setFullname("Nguyễn Công Phát");
		    	sinhvien7.setPassword("12345");
	    		sinhvien7.setGender(Gender.MALE);
	    		sinhvien7.setAvatar(images.getImage("animal7.jpg"));
				usersService.save(sinhvien7);
				
				Users sinhvien8 = new Users();
		    	sinhvien8.setEmail("theluan@gmail.com");
		    	sinhvien8.setFullname("Lý Thế Luân");
		    	sinhvien8.setPassword("12345");
	    		sinhvien8.setGender(Gender.MALE);
	    		sinhvien8.setAvatar(images.getImage("animal8.jpg"));
				usersService.save(sinhvien8);
				
				Users sinhvien9 = new Users();
		    	sinhvien9.setEmail("mylan@gmail.com");
		    	sinhvien9.setFullname("Đỗ Mỹ Lan");
		    	sinhvien9.setPassword("12345");
	    		sinhvien9.setGender(Gender.FEMALE);
	    		sinhvien9.setAvatar(images.getImage("animal2.jpg"));
				usersService.save(sinhvien9);
				
				//Inn1
				InnModel newInnModel = new InnModel();
				newInnModel.setAddress("123 Linh Trung, Thủ Đức, TPHCM");
				newInnModel.setDescribe("Phòng trọ an ninh, cách trường ĐHSPKT 2km");
				newInnModel.setPhoneNumber("0123456789");
				newInnModel.setPrice((double) 3000000);
				newInnModel.setPriceELec((double) 2000);
				newInnModel.setPriceWater((double) 2000);
				newInnModel.setSize(2);
				newInnModel.setProposedId(sinhvien1.getUserId());
				
				List<String> imageArr = new ArrayList<>() ;
				imageArr.add("tro1.jpg");
				imageArr.add("tro2.jpg");
				imageArr.add("tro3.jpg");
				
				innService.recommendInn(newInnModel, imageArr);
				
				Inn inn = innService.findById((long) 1).get();
				inn.setConfirmedById(manager);
				inn.setIsConfirmed(true);
				innService.save(inn);
				
				//Inn2
				InnModel newInnModel2 = new InnModel();
				newInnModel2.setAddress("386 Lê Văn Chí, Thủ Đức, TPHCM");
				newInnModel2.setDescribe("Phòng trọ an ninh, rẻ, cô chủ dễ tính tốt bụng");
				newInnModel2.setPhoneNumber("0987654321");
				newInnModel2.setPrice((double) 1600000);
				newInnModel2.setPriceELec((double) 1500);
				newInnModel2.setPriceWater((double) 1600);
				newInnModel2.setSize(2);
				newInnModel2.setProposedId(sinhvien1.getUserId());
				
				
				List<String> imageArr2 = new ArrayList<>() ;
				imageArr2.add("tro4.jpg");
				imageArr2.add("tro9.jpg");
				
				innService.recommendInn(newInnModel2, imageArr2);
				
				Inn inn2 = innService.findById((long) 2).get();
				inn2.setConfirmedById(manager);
				inn2.setIsConfirmed(true);
				innService.save(inn2);
				
				//Inn3
				InnModel newInnModel3 = new InnModel();
				newInnModel3.setAddress("100 Hoàng Diệu, Thủ Đức, TPHCM");
				newInnModel3.setDescribe("Phòng trọ an ninh, rộng, giờ giấc tự do, chỉ cho sinh viên ở");
				newInnModel3.setPhoneNumber("0333678333");
				newInnModel3.setPrice((double) 2000000);
				newInnModel3.setPriceELec((double) 1800);
				newInnModel3.setPriceWater((double) 1800);
				newInnModel3.setSize(3);
				newInnModel3.setProposedId(sinhvien1.getUserId());
				
				
				List<String> imageArr3 = new ArrayList<>() ;
				imageArr3.add("tro5.jpg");
				imageArr3.add("tro6.jpg");
				
				innService.recommendInn(newInnModel3, imageArr3);
				
				Inn inn3 = innService.findById((long) 3).get();
				inn3.setConfirmedById(manager);
				inn3.setIsConfirmed(true);
				innService.save(inn3);
				
				//Inn4
				InnModel newInnModel4 = new InnModel();
				newInnModel4.setAddress("200 Hoàng Diệu, Thủ Đức, TPHCM");
				newInnModel4.setDescribe("Phòng trọ giá rẻ, chung chủ, 23 giờ đóng cửa, gần trường Đại Học Sư Phạm Kỹ Thuật - TPHCM");
				newInnModel4.setPhoneNumber("0888234567");
				newInnModel4.setPrice((double) 1800000);
				newInnModel4.setPriceELec((double) 1700);
				newInnModel4.setPriceWater((double) 1900);
				newInnModel4.setSize(3);
				newInnModel4.setProposedId(sinhvien2.getUserId());
				
				List<String> imageArr4 = new ArrayList<>() ;
				imageArr4.add("tro8.jpg");
				imageArr4.add("tro10.jpg");
				
				innService.recommendInn(newInnModel4, imageArr4);
				
				//Inn5
				InnModel newInnModel5 = new InnModel();
				newInnModel5.setAddress("234 Lê Văn Việt, Thủ Đức, TPHCM");
				newInnModel5.setDescribe("Phòng trọ an ninh, rộng, đẹp, có chỗ để xe, wifi miễn phí, khóa cổng sau 22 giờ 30");
				newInnModel5.setPhoneNumber("0333678333");
				newInnModel5.setPrice((double) 5000000);
				newInnModel5.setPriceELec((double) 2200);
				newInnModel5.setPriceWater((double) 2200);
				newInnModel5.setSize(5);
				newInnModel5.setProposedId(sinhvien2.getUserId());
				
				
				List<String> imageArr5 = new ArrayList<>() ;
				imageArr5.add("tro11.jpg");
				imageArr5.add("tro12.jpg");
				imageArr5.add("tro13.jpg");
				
				innService.recommendInn(newInnModel5, imageArr5);
				
				Inn inn5 = innService.findById((long) 5).get();
				inn5.setConfirmedById(manager);
				inn5.setIsConfirmed(true);
				innService.save(inn5);
				
				//Inn6
				InnModel newInnModel6 = new InnModel();
				newInnModel6.setAddress("123 Lê Văn Chí, Thủ Đức, TPHCM");
				newInnModel6.setDescribe("Phòng trọ an ninh, đẹp, chung chủ");
				newInnModel6.setPhoneNumber("0678555444");
				newInnModel6.setPrice((double) 2500000);
				newInnModel6.setPriceELec((double) 2000);
				newInnModel6.setPriceWater((double) 2000);
				newInnModel6.setSize(3);
				newInnModel6.setProposedId(sinhvien3.getUserId());
				
				List<String> imageArr6 = new ArrayList<>() ;
				imageArr6.add("tro14.jpg");
				imageArr6.add("tro17.jpg");
				imageArr6.add("tro18.jpg");
				
				innService.recommendInn(newInnModel6, imageArr6);
				
				//Inn7
				InnModel newInnModel7 = new InnModel();
				newInnModel7.setAddress("123 Lê Văn Việt, Thủ Đức, TPHCM");
				newInnModel7.setDescribe("Phòng trọ an ninh, rẻ");
				newInnModel7.setPhoneNumber("0456789123");
				newInnModel7.setPrice((double) 2000000);
				newInnModel7.setPriceELec((double) 2000);
				newInnModel7.setPriceWater((double) 2000);
				newInnModel7.setSize(3);
				newInnModel7.setProposedId(sinhvien3.getUserId());
				
				List<String> imageArr7 = new ArrayList<>() ;
				imageArr7.add("tro15.jpg");
				imageArr7.add("tro16.jpg");
				
				innService.recommendInn(newInnModel7, imageArr7);
				
				//Question1
				Question question = new Question();
				question.setAskedId(sinhvien2);
				question.setTitle("Rút môn");
				iQuestionService.save(question);
				
				Message ask = new Message();
				ask.setMessage("Dạ cho em hỏi là môn thực tập có được rút online không ạ, và nếu không thì làm sao để rút và có điều kiện gì không ạ?");
				ask.setQuestionId(question);
				ask.setUserId(sinhvien2);
				
				messageService.save(ask);
				
				Message answer = new Message();
				answer.setMessage("Em đến văn phòng khoa để được hướng dẫn nhé!");
				answer.setQuestionId(question);
				answer.setUserId(tuvanvien1);
				messageService.save(answer);
				
				question.setAnswererId(tuvanvien1);
				iQuestionService.save(question);
				
				
				//Question2
				Question question2 = new Question();
				question2.setAskedId(sinhvien1);
				question2.setTitle("Thắc mắc thời hạn nộp bằng quy đổi tín chỉ anh văn");
				iQuestionService.save(question2);
				
				Message ask2 = new Message();
				ask2.setMessage("Em chào thầy cô, em là sinh viên K20 em muốn biết về thời hạn nộp bằng anh văn trong kỳ này và quy đổi tín chỉ anh văn sẽ trừ bao nhiêu điểm ạ!");
				ask2.setQuestionId(question2);
				ask2.setUserId(sinhvien1);
				
				messageService.save(ask2);
				
				Message answer2 = new Message();
				answer2.setMessage("Chào em, em có thể nộp từ 12/6 đến 17/6, và nộp sẽ bị trừ 1 điểm!");
				answer2.setQuestionId(question2);
				answer2.setUserId(tuvanvien2);
				messageService.save(answer2);

				question2.setAnswererId(tuvanvien2);
				iQuestionService.save(question2);
				
				//Question3
				Question question3 = new Question();
				question3.setAskedId(sinhvien1);
				question3.setTitle("Miễn thi AVDR");
				iQuestionService.save(question3);
				
				Message ask3 = new Message();
				ask3.setMessage("Em chào phòng Đào Tạo ạ. Em có 1 câu hỏi là hiện tại e thấy có thông báo là bằng Aptis vẫn còn được chấp nhận để quy đổi miễn thi avdr đúng không ạ");
				ask3.setQuestionId(question3);
				ask3.setUserId(sinhvien1);
				
				messageService.save(ask3);
				
				Message answer3 = new Message();
				answer3.setMessage("Em chờ thông báo từ website phòng đạo tạo nha");
				answer3.setQuestionId(question3);
				answer3.setUserId(tuvanvien2);
				messageService.save(answer3);

				question3.setAnswererId(tuvanvien2);
				iQuestionService.save(question3);
				
				//Question4
				Question question4 = new Question();
				question4.setAskedId(sinhvien3);
				question4.setTitle("Học phí khi nộp bằng Toeic để chuyển điểm các học phần Anh Văn");
				iQuestionService.save(question4);
				
				Message ask4 = new Message();
				ask4.setMessage("Em là sinh viên K20 khoa CNTT, em muốn hỏi là khi nộp bằng Toeic để chuyển điểm các học phần Anh Văn thì có phải đóng học phí cho các học phần đó không ạ");
				ask4.setQuestionId(question4);
				ask4.setUserId(sinhvien3);
				
				messageService.save(ask4);
				
				Message answer4 = new Message();
				answer4.setMessage("Đóng học phí chứ em, đóng 50% nha");
				answer4.setQuestionId(question4);
				answer4.setUserId(tuvanvien2);
				messageService.save(answer4);

				question4.setAnswererId(tuvanvien2);
				iQuestionService.save(question4);
				
				//Question5
				Question question5 = new Question();
				question5.setAskedId(sinhvien4);
				question5.setTitle("Quên mật khẩu mail trường");
				iQuestionService.save(question5);
				
				Message ask5 = new Message();
				ask5.setMessage("Dạ thầy cô cho em hỏi là em bị quên mật khẩu Mail của trường mình, và em có thử bấm vào lấy lại mật khẩu thì nó hiện như hình bên dưới. Vậy bây giờ em phải làm như thế nào để có thể lấy lại được mật khẩu Mail của mình vậy ạ. Em cảm ơn thầy cô!!");
				ask5.setQuestionId(question5);
				ask5.setUserId(sinhvien4);
				
				messageService.save(ask5);
				
	    	}
	    }
	    
	    private void createDataDemo(int number) {
	    	for(int i = 1; i <= number; i++) {
	    		Users user = new Users();
		    	user.setEmail("user" + String.valueOf(i) +"@gmail.com");
		    	user.setFullname("Nguyễn Văn An" + String.valueOf(i) );
		    	user.setPassword("12345");
		    	user.setAvatar("ava.png");
		    	if(i % 2 == 0)
		    		user.setGender(Gender.MALE);
		    	else
		    		user.setGender(Gender.FEMALE);
				usersService.save(user);
				
				// Thêm Inn
				InnModel newInnModel = new InnModel();
				newInnModel.setAddress("123 Linh Trung, Thủ Đức");
				newInnModel.setDescribe("Phòng trọ an ninh, cách trường ĐHSPKT 2km");
				newInnModel.setPhoneNumber("0123456789");
				newInnModel.setPrice((double) 2000000);
				newInnModel.setPriceELec((double) 2000);
				newInnModel.setPriceWater((double) 2000);
				newInnModel.setSize(2);
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
				
				//Question
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
