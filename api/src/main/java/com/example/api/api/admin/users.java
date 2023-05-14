package com.example.api.api.admin;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.Users;
import com.example.api.model.UserModel;
import com.example.api.service.IUsersService;
import com.example.api.utils.apiResponse.ApiResponseSimple;
import com.example.api.utils.apiResponse.ApiResponseWithMeta;
import com.example.api.utils.apiResponse.ApiResponseWithResult;

@RestController
@RequestMapping("api/admin/users")
public class users {
	@Autowired
	IUsersService userService;
	
	@GetMapping("")
	public ResponseEntity<?> getUsers(
			@RequestParam(value = "type", defaultValue = "page") String type,
			@RequestParam(value = "offset", defaultValue = "0") Integer  offset,
			@RequestParam(value = "limit", defaultValue = "10") Integer  limit,
			@RequestParam(value = "ascending", defaultValue = "true") Boolean  ascending,
			@RequestParam(value = "isActive", defaultValue = "true") Boolean  isActive,
			@RequestParam(value = "name", defaultValue = "") String  name
			){
		 
		if(type.equals("all")) {
			//get all users
			List<UserModel> users = userService.findAll();
			ApiResponseWithResult apiResponse = new ApiResponseWithResult(false, "ok", users);
			return ResponseEntity.ok(apiResponse);
		}
		else if(type.equals("page")){
			Pageable pageable = PageRequest.of(offset, limit,
					ascending?Sort.by("userId").ascending():Sort.by("userId").descending());
			List<UserModel> users = userService.findAll(pageable, isActive, name);
			ApiResponseWithMeta apiResponse = new ApiResponseWithMeta(false, "ok", users, pageable);
			return ResponseEntity.ok(apiResponse);			
		}
		
		return ResponseEntity.ok(new ApiResponseSimple(true, "invalid type"));	
	}
	
	@PutMapping("lock")
	public ResponseEntity<?> lockUser(@RequestParam(value = "id") Long userId){
		Optional<Users> userOptional = userService.findById(userId);
		
		if(userOptional.isEmpty())
			return ResponseEntity.ok(new ApiResponseSimple(true, "user not found"));	
		
		Users user = userOptional.get();
		user.setActive(false);
		userService.save2(user);
		
		return ResponseEntity.ok(new ApiResponseSimple(false, "locked"));	
	}
	
	@PostMapping("create")
	public ResponseEntity<?> createUser(@RequestBody Users user){
		// check user có tồn tại
		if(userService.findByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.ok(new ApiResponseSimple(true, "tài khoản đã tồn tại"));
		}
		userService.save(user);
		return ResponseEntity.ok(new ApiResponseSimple(false, "created"));
	}
}
