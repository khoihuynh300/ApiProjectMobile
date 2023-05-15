package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Notification;
import com.example.api.entity.Users;

public interface NotifyRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByUserId(Users userId);
}
