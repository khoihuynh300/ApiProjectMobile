package com.example.api.service;

import java.util.List;

import com.example.api.model.InnModel;

public interface IInnService {
	public List<InnModel> getAllInns();
	public InnModel readInnById(Long _id);
	public List<InnModel> searchInn(String address, Double gtePrice, Double ltePrice);
}
