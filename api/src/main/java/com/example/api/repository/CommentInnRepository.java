package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.CommentInn;
import com.example.api.entity.Inn;

public interface CommentInnRepository extends JpaRepository<CommentInn, Long>{
	public List<CommentInn> findByInnId(Inn innId);
}
