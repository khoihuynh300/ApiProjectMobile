package com.example.api.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.utils.PropertiesExtractor;

@RestController
@RequestMapping("api")
public class imagesAPI {
	private String imagedir = PropertiesExtractor.getProperty("application.imagedir");
	
	@GetMapping("upload/{imageName:.+}")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
		InputStream imageStream = new FileInputStream(new File(imagedir + imageName));
		byte[] imageBytes = IOUtils.toByteArray(imageStream);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(imageBytes.length);

		return new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.OK);
	}
}
