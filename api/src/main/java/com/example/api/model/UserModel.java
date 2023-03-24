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
	private String password;
	private String fullname;
	private String avartar;
	private String role;
	private Boolean enable;
	private Boolean active;
}
