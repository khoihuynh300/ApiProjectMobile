package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Inn;
import com.example.api.model.ImageModel;
import com.example.api.repository.ImageInnRepository;
import com.example.api.service.ImageInnService;

@Service
public class ImageInnServiceImpl implements ImageInnService{
	@Autowired
    ImageInnRepository imageInnRepository;

	@Override
    public List<ImageModel> getAllImagesByInnId(Long innId) {
		Inn inn = new Inn();
		inn.setInnId(innId);
		
		List<ImageInn> imageInns = imageInnRepository.findByInnId(inn);
		
		List<ImageModel> imageModels = new ArrayList<>();
		for (ImageInn imageInn : imageInns) {
			ImageModel innModel = new ImageModel();
			BeanUtils.copyProperties(imageInn, innModel);
			
			imageModels.add(innModel);
		}
		
        return imageModels;
    }
	
	@Override
	public ImageModel getMainImageByInnId(Long innId) {
		Inn inn = new Inn();
		inn.setInnId(innId);
		
		if(imageInnRepository.findByInnId(inn).size() > 0)
		{
			ImageInn imageInn = imageInnRepository.findByInnId(inn).get(0);
			
			ImageModel innModel = new ImageModel();
			BeanUtils.copyProperties(imageInn, innModel);
			return innModel;
		}
		return null;
	}
}
