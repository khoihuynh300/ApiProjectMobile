package com.example.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Notification;
import com.example.api.entity.Users;
import com.example.api.repository.NotifyRepository;
import com.example.api.service.INotifyService;

@Service
public class NotifyServiceImpl implements INotifyService {

	@Autowired NotifyRepository notifyRepository;

	@Override
	public <S extends Notification> S save(S entity) {
		return notifyRepository.save(entity);
	}

	@Override
	public List<Notification> findByUserId(Users userId) {
		return notifyRepository.findByUserId(userId);
	}

	@Override
	public Optional<Notification> findById(Long id) {
		return notifyRepository.findById(id);
	}
	
	
	
}
