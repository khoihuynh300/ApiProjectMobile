package com.example.api.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.Users;
import com.example.api.service.IUsersService;

@RestController
@RequestMapping("api")
public class test {

	@Autowired
	IUsersService userService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

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

	@GetMapping("/getImage2")
	@ResponseBody
	public ResponseEntity<byte[]> getImage2() throws IOException {
		InputStream imageStream = new FileInputStream(new File("D:\\image\\bgNight.png"));
		byte[] imageBytes = IOUtils.toByteArray(imageStream);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(imageBytes.length);

		return new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.OK);
	}

	@PostMapping("/uploadImage")
	@ResponseBody
	public String uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			File newFile = new File("D:\\image\\" + fileName);
			FileCopyUtils.copy(bytes, newFile);
			return "Upload thành công!";
		} else {
			return "Upload thất bại: File trống!";
		}
	}

	@GetMapping("testmail")
	public String testmail() {
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo("khoihuynh300@gmail.com");
			mailMessage.setText("hello");
			mailMessage.setSubject("hello");

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		catch (Exception e) {
			System.err.println(e.toString());
			return "Error while Sending Mail";
		}
	}

}
