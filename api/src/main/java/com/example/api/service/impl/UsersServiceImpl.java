package com.example.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Users;
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
	
	
}
