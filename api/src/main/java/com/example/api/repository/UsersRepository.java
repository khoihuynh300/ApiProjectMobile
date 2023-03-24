package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
