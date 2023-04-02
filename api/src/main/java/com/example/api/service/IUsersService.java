package com.example.api.service;

import com.example.api.entity.Users;

public interface IUsersService {

	<S extends Users> S save(S entity);

	long count();

	Users findByEmail(String email);

}
