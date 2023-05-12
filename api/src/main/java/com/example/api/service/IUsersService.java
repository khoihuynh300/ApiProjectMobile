package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.api.entity.Users;
import com.example.api.model.UserModel;

public interface IUsersService {

	<S extends Users> S save(S entity);

	long count();

	Optional<Users> findByEmail(String email);

	Optional<Users> findById(Long id);

	List<UserModel> findAll(Pageable pageable);

	List<UserModel> findAll();

	List<UserModel> findAll(Pageable pageable, Boolean isActive, String name);

}
