package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Inn;

public interface ImageInnRepository extends JpaRepository<ImageInn, Long>{
	public List<ImageInn> findByInnId(Inn innId);
}
