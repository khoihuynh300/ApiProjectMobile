package com.example.api.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class images {
	private static String imagedir;
	private static String serverAddress;
	private static String port;
	
	static {
		imagedir = PropertiesExtractor.getProperty("application.imagedir");
		serverAddress = PropertiesExtractor.getProperty("server.address");
		port = PropertiesExtractor.getProperty("server.port");
	}
	
	public static  String saveImage(MultipartFile file) throws IOException {

		String fileName = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		File newFile = new File(imagedir + fileName);
		FileCopyUtils.copy(bytes, newFile);
		return "http://" + serverAddress + ":" + port + "/api/upload/" + fileName;
	}
	
	public static String getDefaultImage() {

		return "http://" + serverAddress + ":" + port + "/api/upload/default-avatar.png";
	}
}
