package com.example.api.model;

import com.example.api.entity.Users.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	private Long userId;
	private String email;
	private String fullname;
	private Gender gender;
	private String avatar;
	private String role;
}
