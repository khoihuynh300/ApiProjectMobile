package com.example.api.api;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.Users;
import com.example.api.model.UserModel;
import com.example.api.service.IUsersService;

@RestController
@RequestMapping("api/")
public class UserAPI {
	@Autowired
	IUsersService userService;
	
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
}
