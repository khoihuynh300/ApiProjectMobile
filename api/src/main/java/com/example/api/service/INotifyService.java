package com.example.api.service;

import java.util.List;
import java.util.Optional;

import com.example.api.entity.Notification;
import com.example.api.entity.Users;

public interface INotifyService {

	List<Notification> findByUserId(Users userId);

	<S extends Notification> S save(S entity);

	Optional<Notification> findById(Long id);

}
