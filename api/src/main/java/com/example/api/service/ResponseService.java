package com.example.api.service;

import java.util.HashMap;

public class ResponseService {
	
	public static HashMap<String, Object> get(boolean error, String message) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		return map;
	}
}
