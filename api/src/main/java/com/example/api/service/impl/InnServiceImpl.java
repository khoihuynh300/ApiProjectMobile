package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<InnModel> searchInn(String address, Double gtePrice, Double ltePrice, int size){
		List<Inn> inns = innRepository.searchInn(address, gtePrice, ltePrice, size);
		return addMainImage(inns);
	}
	
	@Override
	public void recommendInn(InnModel innModel, List<String> imageArr) {
		Inn inn = new Inn();
		BeanUtils.copyProperties(innModel, inn);
		inn.setProposedById(usersServiceImpl.findById(innModel.getProposedId()).get());
		innRepository.save(inn);
		
		List<Inn> inns = innRepository.findAll();
		
		imageInnService.addImageInn(imageArr, inns.get(inns.size() - 1));
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

	@Override
	public <S extends Inn> S save(S entity) {
		return innRepository.save(entity);
	}

	@Override
	public Optional<Inn> findById(Long id) {
		return innRepository.findById(id);
	}

	@Override
	public List<Inn> findAll(Pageable pageable) {
		return innRepository.findAll(pageable).getContent();
	}
	
	
	
}
