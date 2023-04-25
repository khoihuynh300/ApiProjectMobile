package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Inn;
import com.example.api.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	public List<Message> findByInnId(Inn innId);
}
