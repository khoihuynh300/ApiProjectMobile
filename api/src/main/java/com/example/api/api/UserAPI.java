package com.example.api.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.Inn;
import com.example.api.entity.Users;
import com.example.api.entity.Users.Gender;
import com.example.api.model.InnModel;
import com.example.api.model.UserModel;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.MailService;
import com.example.api.service.OtpService;
import com.example.api.service.ResponseService;
import com.example.api.utils.images;
import com.example.api.utils.apiResponse.ApiResponseWithResult;

@RestController
@RequestMapping("api/")
public class UserAPI {
	
	@Autowired
	IInnService iInnService;
	
	@Autowired
	IUsersService userService;

	@Autowired
	MailService mailService;

	@Autowired
	private OtpService otpService;

	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {

		UserModel userModel = new UserModel();

		Optional<Users> usersOP = userService.findByEmail(email);

//		if (usersOP.isEmpty() || !password.equals(usersOP.get().getPassword())) {
//			return ResponseEntity.ok(ResponseService.get(true, "Tài khoản hoặc mật khẩu không chính xác"));
//		}
		if (usersOP.isEmpty() || !BCrypt.checkpw(password, usersOP.get().getPassword())) {
			return ResponseEntity.ok(ResponseService.get(true, "Tài khoản hoặc mật khẩu không chính xác"));
		}

		BeanUtils.copyProperties(usersOP.get(), userModel);

		HashMap<String, Object> map = ResponseService.get(false, "Đăng nhập thành công");
		map.put("user", userModel);

		return ResponseEntity.ok(map);
	}

	@PostMapping("signup")
	public ResponseEntity<Object> signup(@RequestParam(value = "email") String email,
			@RequestParam(value = "fname") String fname, @RequestParam(value = "gender") Gender gender,
			@RequestParam(value = "password") String password) {

		Optional<Users> usersOP = userService.findByEmail(email);

		if (!usersOP.isEmpty()) {
			return ResponseEntity.ok(ResponseService.get(true, "Tài khoản đã tồn tại"));
		}
		mailService.sendOtpCode(email);
		return ResponseEntity.ok(ResponseService.get(false, "Đã gửi OTP Code"));
	}

	@PostMapping("signup/verify")
	public ResponseEntity<Object> verify(@RequestParam(value = "email") String email,
			@RequestParam(value = "fname") String fname, @RequestParam(value = "gender") Gender gender,
			@RequestParam(value = "password") String password, @RequestParam(value = "otp") int otpCode) {
//		
		Optional<Users> usersOP = userService.findByEmail(email);
		if (!usersOP.isEmpty()) {
			return ResponseEntity.ok(ResponseService.get(true, "Tài khoản đã tồn tại"));
		}

		if (otpService.getOtp(email) != otpCode) {
			return ResponseEntity.ok(ResponseService.get(true, "OTP Code không chính xác"));
		}
		Users newusers = new Users();
		newusers.setEmail(email);
		newusers.setFullname(fname);
		newusers.setGender(gender);
		newusers.setPassword(password);
		userService.save(newusers);
		return ResponseEntity.ok(ResponseService.get(false, "Đăng ký thành công"));
	}

//	@PostMapping("updateimage")
//	public ResponseEntity<Object> updateimage(@RequestParam("id") Long id,
//            @RequestParam("image") MultipartFile file) throws IOException {
//		if (!file.isEmpty()) {
//			Optional<Users> usersOP = userService.findById(id);
//			if(usersOP.isEmpty()) {
//				return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy user"));
//			}
//			Users user = usersOP.get();
//			
//	        String fileName = file.getOriginalFilename();
//	        byte[] bytes = file.getBytes();
//	        File newFile = new File(imagedir + fileName);
//	        FileCopyUtils.copy(bytes, newFile);
//	        
//	        user.setAvatar("http://"+ serverAddress +":"+port+"/api/upload/" + fileName);
//	        userService.save(user);
//	        
//	        UserModel userModel = new UserModel();
//	        BeanUtils.copyProperties(user, userModel);
//	        
//			HashMap<String, Object> map = ResponseService.get(false, "Thành công");
//			map.put("result", userModel);
//			return ResponseEntity.ok(map);
//	    } else {
//	    	return ResponseEntity.ok(ResponseService.get(true, "Upload thất bại: File trống!"));
//	    }
//	}

//	@PostMapping("update-profile")
//	public ResponseEntity<Object> updateProfile(@RequestParam("id") Long id,
//			@RequestParam(value = "fname") String fname) {
//		Optional<Users> usersOP = userService.findById(id);
//		if(usersOP.isEmpty()) {
//			return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy user"));
//		}
//		
//		Users user = usersOP.get();
//		user.setFullname(fname);
//		userService.save(user);
//		
//		UserModel userModel = new UserModel();
//        BeanUtils.copyProperties(user, userModel);
//		
//		HashMap<String, Object> map = ResponseService.get(false, "thành công");
//		map.put("result", userModel);
//		return ResponseEntity.ok(map);
//	}

	@PostMapping("update-profile")
	public ResponseEntity<Object> updateimage(@RequestParam("id") Long id, @RequestParam(value = "fname") String fname,
			@RequestParam(value = "gender") Gender gender,
			@RequestParam(value = "image", required = false) MultipartFile file) throws IOException {

		Optional<Users> usersOP = userService.findById(id);
		if (usersOP.isEmpty()) {
			return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy user"));
		}
		Users user = usersOP.get();
		user.setFullname(fname);
		user.setGender(gender);
		// kiểm tra người dùng có truyền tham số image vào không nếu không thì ko update
		// image
		if (file != null) {
			// nếu người dùng có truyền image mà file empty thì thông báo lỗi
			if (!file.isEmpty()) {
				String imageUrl = images.saveImage(file);
				user.setAvatar(imageUrl);
			} else {
				return ResponseEntity.ok(ResponseService.get(true, "Upload thất bại: File trống!"));
			}
		}
		userService.save(user);

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(user, userModel);

		HashMap<String, Object> map = ResponseService.get(false, "Thành công");
		map.put("result", userModel);
		return ResponseEntity.ok(map);
	}

	@PostMapping("change-password")
	public ResponseEntity<Object> changePassword(@RequestParam(value = "id") Long id,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "oldpassword") String oldpassword) {
		Optional<Users> usersOP = userService.findById(id);
		if (usersOP.isEmpty()) {
			return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy user"));
		}

		Users user = usersOP.get();
		if (password.equals(user.getPassword())) {
			return ResponseEntity.ok(ResponseService.get(true, "Mật khẩu cũ không chính xác"));
		}
		user.setPassword(password);
		userService.save(user);

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(user, userModel);

		return ResponseEntity.ok(ResponseService.get(false, "thành công"));
	}

	@PostMapping("forget-password")
	public ResponseEntity<Object> forgetPassword(@RequestParam(value = "email") String email,
			@RequestParam(value = "newpassword") String newpassword) {
		Optional<Users> usersOP = userService.findByEmail(email);
		if (usersOP.isEmpty()) {
			return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy user"));
		}

		mailService.sendOtpCode(email);

		return ResponseEntity.ok(ResponseService.get(false, "Đã gửi otp code"));
	}

	@PostMapping("forget-password/verify")
	public ResponseEntity<Object> forgetPasswordVerify(@RequestParam(value = "email") String email,
			@RequestParam(value = "newpassword") String newpassword, @RequestParam(value = "otp") int otpCode) {
		Optional<Users> usersOP = userService.findByEmail(email);
		if (usersOP.isEmpty()) {
			return ResponseEntity.ok(ResponseService.get(true, "Không tìm thấy user"));
		}
		if (otpService.getOtp(email) != otpCode) {
			return ResponseEntity.ok(ResponseService.get(true, "OTP Code không chính xác"));
		}
		Users user = usersOP.get();
		user.setPassword(newpassword);
		userService.save(user);

//		user.getUserId();
		return ResponseEntity.ok(ResponseService.get(false, "đã đặt lại mật khẩu"));
	}
	@GetMapping("users/{user_id}")

	public ResponseEntity<?> getUserById(@PathVariable("user_id") Long userId){
		Users users = userService.findById(userId).get();
		UserModel userModel = new UserModel();
		
		BeanUtils.copyProperties(users, userModel);
		
		ApiResponseWithResult apiResponse = new ApiResponseWithResult(false, "ok", userModel);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("users/{user_id}/inns")
	public ResponseEntity<?> getInnsByUserId(@PathVariable("user_id") Long userId){
		
		Users users = userService.findById(userId).get();
		
		List<InnModel> innList = iInnService.findByProposedById(users);
		ApiResponseWithResult apiResponse = new ApiResponseWithResult(false, "ok", innList);
		return ResponseEntity.ok(apiResponse);
	}
}
