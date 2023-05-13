package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Message;
import com.example.api.entity.Question;

public interface MessageRepository extends JpaRepository<Message, Long>{

	public List<Message> findAllByQuestionId(Question questionId);
}
