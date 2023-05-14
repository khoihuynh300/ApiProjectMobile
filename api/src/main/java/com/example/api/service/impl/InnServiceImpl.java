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
	UsersServiceImpl usersServiceImpl;
	
	@Autowired
	ImageInnService imageInnService;

    public List<InnModel> getAllInns(Boolean isConfirmed){
        List<Inn> inns = innRepository.findAll();
        List<Inn> innsConfirmed = new ArrayList<>();
        List<Inn> innsUnConfirmed = new ArrayList<>();
        for (Inn inn : inns) {
			if (inn.getIsConfirmed())
				innsConfirmed.add(inn);
			else
				innsUnConfirmed.add(inn);
		}
        if(isConfirmed)
        	return addMainImage(innsConfirmed);
        else
        	return addMainImage(innsUnConfirmed);
    }
    
    @Override
    public List<InnModel> getAllInnsConfirmed() {
    	return getAllInns(true);
    }
    
    @Override
	public List<InnModel> getAllInnsUnConfirmed(){
    	return getAllInns(false);
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
        innModel.setProposed(inn.get().getProposedById().getFullname());
        return innModel;
    }
	
	@Override
	public List<InnModel> searchInn(String address, Double gtePrice, Double ltePrice, int size){
		List<Inn> inns = innRepository.searchInn(address, gtePrice, ltePrice, size);
		return addMainImage(inns);
	}
	
	@Override
	public void recommendInn(InnModel innModel, List<String> imageArr) {
		Inn inn = new Inn();
		BeanUtils.copyProperties(innModel, inn);
		inn.setProposedById(usersServiceImpl.findById(innModel.getProposedId()).get());
		inn.setIsConfirmed(false);
		innRepository.save(inn);
		
		imageInnService.addImageInn(imageArr, inn);
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
