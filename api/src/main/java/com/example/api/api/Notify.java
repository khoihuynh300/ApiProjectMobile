package com.example.api.api;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.Notification;
import com.example.api.entity.Users;
import com.example.api.model.NotificationModel;
import com.example.api.service.INotifyService;
import com.example.api.service.IUsersService;
import com.example.api.utils.apiResponse.ApiResponseWithResult;

@RestController
@RequestMapping("api/notify")
public class Notify {
	
	@Autowired INotifyService iNotifyService;
	@Autowired IUsersService iUsersService;

	@GetMapping("{userId}")
	public ResponseEntity<?> getAllNotityByUser(@PathVariable("userId") Long userId){
		Users user = iUsersService.findById(userId).get();
		List<Notification> notificationList = iNotifyService.findByUserId(user);
		
		List<NotificationModel> listModels = new ArrayList<>();
		
		for(Notification not: notificationList) {
			NotificationModel model = new NotificationModel();
			
			BeanUtils.copyProperties(not, model);
			listModels.add(model);
		}
		
		ApiResponseWithResult apiResponse = new ApiResponseWithResult(false, "ok", listModels);
		return ResponseEntity.ok(apiResponse);
	}
	
	
}
