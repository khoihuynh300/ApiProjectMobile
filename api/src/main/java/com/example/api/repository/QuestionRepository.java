package com.example.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.entity.Question;
import com.example.api.entity.Users;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	Optional<Question> findByTitle(String title);
	List<Question> findByTitleContaining(String keyword);
	Optional<Question> findById(Long id);
	Optional<Question> findFirstByOrderByCreatedAtDesc();
	
	List<Question> findByAskedId(Users askedId);
}
