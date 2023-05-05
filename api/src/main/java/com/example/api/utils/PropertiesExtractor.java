package com.example.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesExtractor {
	private static Properties properties;
	static {
		properties = new Properties();
		URL url = new PropertiesExtractor().getClass().getClassLoader().getResource("application.properties");
		try{
			properties.load(new FileInputStream(url.getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
}
