package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.entity.Inn;
import com.example.api.entity.Users;
import com.example.api.model.InnModel;
import com.example.api.repository.InnRepository;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.ImageInnService;
import com.example.api.utils.apiResponse.ApiResponseSimple;
import com.example.api.utils.apiResponse.ApiResponseWithResult;

@Service
public class InnServiceImpl implements IInnService{
	@Autowired
    InnRepository innRepository;
	
	@Autowired
	IUsersService usersServiceImpl;
	
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
			innModel.setProposedId(inn.getProposedById().getUserId());
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
	public List<InnModel> findAll(Pageable pageable) {
		List<Inn> innList = innRepository.findAll(pageable).getContent();
		
		List<InnModel> innModelList = new ArrayList<>();
		
		
		for(Inn inn : innList) {
			InnModel innModel = new InnModel();
			BeanUtils.copyProperties(inn, innModel);
			
			innModel.setProposedId(inn.getProposedById().getUserId());
			if(inn.getIsConfirmed()) {
				innModel.setConfirmedById(inn.getConfirmedById().getUserId());				
			}
			innModel.setMainImage(imageInnService.getMainImageByInnId(inn.getInnId()));
			innModel.setImages(imageInnService.getAllImagesByInnId(inn.getInnId()));
			innModelList.add(innModel);
		}
		return innModelList;
	}
	
	@Override
	public List<InnModel> findAll(Boolean isDeleted,String address, String isConfirmed, Pageable pageable) {
		address = address.trim();
		List<Inn> innList;
		
		if(address.equals("")) {
			// search without address
			if(isConfirmed.equals("all")) {
				innList = innRepository.findByIsDeleted(isDeleted,pageable).getContent();								
			}
			else {
				innList = innRepository.findByIsDeletedAndIsConfirmed(isDeleted, Boolean.valueOf(isConfirmed), pageable).getContent();	
			}
		}
		else {
			//search with contain address
			if(isConfirmed.equals("all")) {
				innList = innRepository.findByAddressContainingAndIsDeleted(address,isDeleted,pageable).getContent();					
			}
			else {
				innList = innRepository.findByAddressContainingAndIsDeletedAndIsConfirmed(address, isDeleted,Boolean.valueOf(isConfirmed), pageable).getContent();
			}
		}
		
		List<InnModel> innModelList = new ArrayList<>();
		
		
		for(Inn inn : innList) {
			InnModel innModel = new InnModel();
			BeanUtils.copyProperties(inn, innModel);
			
			innModel.setProposedId(inn.getProposedById().getUserId());
			if(inn.getIsConfirmed()) {
				innModel.setConfirmedById(inn.getConfirmedById().getUserId());				
			}
			innModel.setMainImage(imageInnService.getMainImageByInnId(inn.getInnId()));
			innModel.setImages(imageInnService.getAllImagesByInnId(inn.getInnId()));
			innModelList.add(innModel);
		}
		return innModelList;
	}

	@Override
	public List<InnModel> findByProposedById(Users users) {
		List<Inn> innList = innRepository.findByProposedById(users);
		
		List<InnModel> innModelList = new ArrayList<>();
		
		
		for(Inn inn : innList) {
			InnModel innModel = new InnModel();
			BeanUtils.copyProperties(inn, innModel);
			
			innModel.setProposedId(inn.getProposedById().getUserId());
			if(inn.getIsConfirmed()) {
				innModel.setConfirmedById(inn.getConfirmedById().getUserId());				
			}
			innModel.setMainImage(imageInnService.getMainImageByInnId(inn.getInnId()));
			innModel.setImages(imageInnService.getAllImagesByInnId(inn.getInnId()));
			innModelList.add(innModel);
		}
		return innModelList;
	}
	
	@Override
	public ResponseEntity<?> addByManager(InnModel innModel) {
		/*
		 * hàm này dành cho người dùng role manager nên isConfirmed = true, ConfirmedId
		 * là id người dùng có role manager, ProposedById = null
		 */
		
		Inn inn = new Inn();
		BeanUtils.copyProperties(innModel, inn);
		Users users = usersServiceImpl.findById(innModel.getProposedId()).get();

		inn.setProposedById(users);
		inn.setConfirmedById(users);
		inn.setIsConfirmed(true);
		innRepository.save(inn);
		

    	return ResponseEntity.ok(new ApiResponseWithResult(true, "created", inn));
	}
	
	
}
