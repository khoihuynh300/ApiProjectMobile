package com.example.api.model;

import java.util.Date;

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
	private Long askedId;
	private Long answererId;
	private Date createdAt;
	private Date updatedAt;
}
