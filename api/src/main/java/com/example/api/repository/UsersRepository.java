package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByEmail(String email);

	Page<Users> findByFullnameContainingAndActive(String fullname,Boolean active, Pageable pageable);
	Page<Users> findByActive(Boolean active, Pageable pageable);
}
