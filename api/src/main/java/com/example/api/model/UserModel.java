package com.example.api.model;

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
	private String avatar;
	private String role;
}
