package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Inn;
import com.example.api.model.InnModel;
import com.example.api.repository.InnRepository;
import com.example.api.service.IInnService;
import com.example.api.service.ImageInnService;

@Service
public class InnServiceImpl implements IInnService{
	@Autowired
    InnRepository innRepository;
	
	@Autowired
	ImageInnService imageInnService;

	@Override
    public List<InnModel> getAllInns() {
        List<Inn> inns = innRepository.findAll();
        return addMainImage(inns);
    }
	
	@Override
	public InnModel readInnById(Long _id) {
		Optional<Inn> inn = innRepository.findById(_id);
        InnModel innModel = new InnModel();
        
        BeanUtils.copyProperties(inn.get(), innModel);
        if(imageInnService.getAllImagesByInnId(_id) != null)
        	innModel.setImages(imageInnService.getAllImagesByInnId(_id));
        else
        	innModel.setImages(null);
        return innModel;
    }
	
	@Override
	public List<InnModel> searchInn(String address, Double gtePrice, Double ltePrice){
		List<Inn> inns = innRepository.searchInn(address, gtePrice, ltePrice);
		return addMainImage(inns);
	}
	
	private List<InnModel> addMainImage(List<Inn> inns)
	{
		List<InnModel> innModels = new ArrayList<>();
		for (Inn inn : inns) {
			InnModel innModel = new InnModel();
			BeanUtils.copyProperties(inn, innModel);
			innModel.setProposed(inn.getProposedById().getFullname());
			innModel.setMainImage(imageInnService.getMainImageByInnId(inn.getInnId()));
			innModels.add(innModel);
		}
		
		return innModels;
	}
}
