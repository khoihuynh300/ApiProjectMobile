package com.example.api.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class test {
	@RequestMapping("test")
	public String test1() {
		System.err.println("this is test");
		return "test2";
	}
}
