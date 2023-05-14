package com.example.api.service.impl;

import java.util.Date;

import com.example.api.entity.Question;
import com.example.api.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel2 {
	private Long messageId;
	private String message;
	private String image;
	private Date createdAt;
	private Date updatedAt;
	private String username;
	private Long userId;
	private Long questionId;
}