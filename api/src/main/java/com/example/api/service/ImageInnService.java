package com.example.api.service;

import java.util.List;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Inn;
import com.example.api.model.ImageModel;

public interface ImageInnService {
	public List<ImageModel> getAllImagesByInnId(Long innId);
	public ImageModel getMainImageByInnId(Long innId);
	public void addImageInn(List<String> imageArr, Inn inn);
}
