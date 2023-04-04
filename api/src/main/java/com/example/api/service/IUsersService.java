package com.example.api.service;

import java.util.Optional;

import com.example.api.entity.Users;

public interface IUsersService {

	<S extends Users> S save(S entity);

	long count();

	Optional<Users> findByEmail(String email);

	Optional<Users> findById(Long id);

}
