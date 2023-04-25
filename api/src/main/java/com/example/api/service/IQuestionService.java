package com.example.api.service;

import java.util.List;

import com.example.api.entity.Question;
import com.example.api.entity.Users;
import com.example.api.model.QuestionModel;

public interface IQuestionService {

	<S extends Question> S save(S entity);

	long count();

	QuestionModel findByTitle(String title);

	List<QuestionModel> findByTitleContaining(String keyword);

	List<QuestionModel> findAll();

	QuestionModel findById(Long id);

	void deleteById(Long id);

	void updateQuestion(QuestionModel questionModel, Users userAsked, Users userAnswered);

}
