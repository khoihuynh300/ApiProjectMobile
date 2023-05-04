package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.entity.Users;
import com.example.api.model.UserModel;
import com.example.api.repository.UsersRepository;
import com.example.api.service.IUsersService;

@Service
public class UsersServiceImpl implements IUsersService {
	@Autowired
	UsersRepository usersRepository;

	@Override
	public <S extends Users> S save(S entity) {
		return usersRepository.save(entity);
	}

	@Override
	public long count() {
		return usersRepository.count();
	}

	@Override
	public Optional<Users> findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	@Override
	public Optional<Users> findById(Long id) {
		return usersRepository.findById(id);
	}

	@Override
	public List<UserModel> findAll(Pageable pageable) {
		List<Users> usersList = usersRepository.findAll(pageable).getContent();
		
		List<UserModel> userModelList = new ArrayList<>();
		for (Users user : usersList) {
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(user, userModel);
			userModelList.add(userModel);
		}
		
		return userModelList;
	}

	@Override
	public List<UserModel> findAll() {
		
		List<Users> usersList= usersRepository.findAll();
		
		List<UserModel> userModelList = new ArrayList<>();
		for (Users user : usersList) {
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(user, userModel);
			userModelList.add(userModel);
		}
		
		return userModelList;
	}
	
}
