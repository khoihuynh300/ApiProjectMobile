package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.api.entity.Inn;
import com.example.api.model.InnModel;

public interface IInnService {
	public List<InnModel> getAllInns();
	public InnModel readInnById(Long _id);
	public void recommendInn(InnModel innModel, List<String> imageArr);
	public List<InnModel> searchInn(String address, Double gtePrice, Double ltePrice, int size);
	<S extends Inn> S save(S entity);
	Optional<Inn> findById(Long id);
	List<InnModel> findAll(Pageable pageable);
	List<InnModel> findAll(Boolean isDeleted, String address, String isConfirmed, Pageable pageable);
}
