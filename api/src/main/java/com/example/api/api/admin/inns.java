package com.example.api.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.InnModel;
import com.example.api.service.IInnService;

@RestController
@RequestMapping("api/admin/inns")
public class inns {
	
	@Autowired
	IInnService iInnService;

	@GetMapping
	public ResponseEntity<?> getInns(){
		List<InnModel> inns = iInnService.getAllInns();
		return ResponseEntity.ok(inns);
	}
}
