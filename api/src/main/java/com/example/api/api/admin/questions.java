package com.example.api.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.IQuestionService;

@RestController
@RequestMapping("api/admin/users")
public class questions {
	@Autowired
	IQuestionService iQuestionService;
	
	
}
