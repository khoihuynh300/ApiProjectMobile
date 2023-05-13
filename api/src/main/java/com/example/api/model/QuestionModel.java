package com.example.api.model;

import java.util.Date;

import com.example.api.entity.Users;

import com.example.api.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
	private Long questionId;
	private String title;
	private Double view;
	private Users askedId;
	private Users answererId;
	private Date createdAt;
	private Date updatedAt;
}
