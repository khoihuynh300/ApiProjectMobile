package com.example.api.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.Users;
import com.example.api.model.UserModel;
import com.example.api.service.IUsersService;

@RestController
@RequestMapping("api/")
public class UserAPI {
	@Autowired
	IUsersService userService;
	
	@Value("${server.address}")
	private String serverAddress;
	
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		UserModel userModel = new UserModel();
		
		Users users = userService.findByEmail(email);

		HashMap<String, Object> map = new HashMap<>();
		
		if(users == null || !password.equals(users.getPassword())) {
			map.put("error", false);
			map.put("message", "Tài khoản hoặc mật khẩu không chính xác");
			return ResponseEntity.ok(map);
		}
		
		BeanUtils.copyProperties(users, userModel);
		
		map.put("error", false);
		map.put("message", "Đăng nhập thành công");
		map.put("user", userModel);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("signup")
	public ResponseEntity<Object> signup(@RequestParam(value = "email") String email,
			@RequestParam(value = "fname") String fname,
			@RequestParam(value = "password") String password) {
		
		Users users = userService.findByEmail(email);

		HashMap<String, Object> map = new HashMap<>();
		
		if(users != null) {
			map.put("error", false);
			map.put("message", "Tài khoản đã tồn tại");
			return ResponseEntity.ok(map);
		}
		Users newusers = new Users();
		newusers.setEmail(email);
		newusers.setFullname(fname);
		newusers.setPassword(password);
		userService.save(newusers);
		
		map.put("error", false);
		map.put("message", "Đăng ký thành công");
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("updateimage")
	public ResponseEntity<Object> updateimage(@RequestParam("id") Long id,
            @RequestParam("image") MultipartFile file) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		if (!file.isEmpty()) {
			Optional<Users> usersOP = userService.findById(id);
			if(usersOP.isEmpty()) {
				map.put("error", true);
				map.put("message", "Không tìm thấy user");
				return ResponseEntity.ok(map);
			}
			Users user = usersOP.get();
			
	        String fileName = file.getOriginalFilename();
	        byte[] bytes = file.getBytes();
	        File newFile = new File("D:\\image\\" + fileName);
	        FileCopyUtils.copy(bytes, newFile);
	        
	        user.setAvatar("http://192.168.1.18:8090/api/upload/" + fileName);
	        userService.save(user);
	        
	        UserModel userModel = new UserModel();
	        BeanUtils.copyProperties(user, userModel);
	        
	        map.put("error", false);
			map.put("message", "Thành công");
			map.put("result", userModel);
	        return ResponseEntity.ok(map);
	    } else {
	    	map.put("error", true);
			map.put("message", "Upload thất bại: File trống!");
	    	return ResponseEntity.ok(map);
	    }
	}
	
	@PostMapping("update-profile")
	public ResponseEntity<Object> updateProfile(@RequestParam(value = "id") Long id,
			@RequestParam(value = "fname") String fname) {
		HashMap<String, Object> map = new HashMap<>();
		Optional<Users> usersOP = userService.findById(id);
		if(usersOP.isEmpty()) {
			map.put("error", true);
			map.put("message", "Không tìm thấy user");
			return ResponseEntity.ok(map);
		}
		
		Users user = new Users();
		user.setFullname(fname);
		userService.save(user);
		
		UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
		
		map.put("error", false);
		map.put("message", "thành công");
		map.put("result", userModel);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("change-password")
	public ResponseEntity<Object> changePassword(@RequestParam(value = "id") Long id,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "oldpassword") String oldpassword) {
		HashMap<String, Object> map = new HashMap<>();
		Optional<Users> usersOP = userService.findById(id);
		if(usersOP.isEmpty()) {
			map.put("error", true);
			map.put("message", "Không tìm thấy user");
			return ResponseEntity.ok(map);
		}
		
		Users user = usersOP.get();
		if(password.equals(user.getPassword())) {
			map.put("error", true);
			map.put("message", "Mật khẩu cũ không chính xác");
			return ResponseEntity.ok(map);
		}
		user.setPassword(password);
		userService.save(user);
		
		UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
		
		map.put("error", false);
		map.put("message", "thành công");
		return ResponseEntity.ok(map);
	}
}
