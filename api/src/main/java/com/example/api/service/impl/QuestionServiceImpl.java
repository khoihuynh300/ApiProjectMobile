package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Question;
import com.example.api.entity.Users;
import com.example.api.model.QuestionModel;
import com.example.api.repository.QuestionRepository;
import com.example.api.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	QuestionRepository questionRepository;

	@Override
	public <S extends Question> S save(S entity) {
		return questionRepository.save(entity);
	}

	@Override
	public long count() {
		return questionRepository.count();
	}

	@Override
	public QuestionModel findByTitle(String title) {
		QuestionModel questionModel = new QuestionModel();
		Optional<Question> questionEntity = questionRepository.findByTitle(title);
		BeanUtils.copyProperties(questionEntity.get(), questionModel);
		return questionModel;
	}

	@Override
	public List<QuestionModel> findByTitleContaining(String keyword) {
		List<QuestionModel> listQuestionsModel = new ArrayList<>();
		List<Question> listQuestionsEntity = questionRepository.findByTitleContaining(keyword);
		for (Question question : listQuestionsEntity) {
			QuestionModel newQuestionModel = new QuestionModel();
			BeanUtils.copyProperties(question, newQuestionModel);
			listQuestionsModel.add(newQuestionModel);
		}
		return listQuestionsModel;
	}

	@Override
	public List<QuestionModel> findAll() {
		List<QuestionModel> listQuestionsModel = new ArrayList<>();
		List<Question> listQuestionsEntity = questionRepository.findAll();
		for (Question question : listQuestionsEntity) {
			QuestionModel newQuestionModel = new QuestionModel();
			BeanUtils.copyProperties(question, newQuestionModel);
//			newQuestionModel.setAskedId(question.getAskedId().getUserId());
//			newQuestionModel.setAnswererId(question.getAnswererId().getUserId());
			listQuestionsModel.add(newQuestionModel);
		}
		return listQuestionsModel;
	}

	@Override
	public Question findById(Long id) {
//		QuestionModel questionModel = new QuestionModel();
		Optional<Question> questionEntity = questionRepository.findById(id);
		if (questionEntity.isPresent()) {
//			BeanUtils.copyProperties(questionEntity.get(), questionModel);
			return questionEntity.get();
		}else {
			Question question2 = new Question();
			question2.setTitle("Không tìm thấy!");
			return question2;
		}
	}

	@Override
	public void deleteById(Long id) {
		questionRepository.deleteById(id);
	}
	
	@Override
	public void updateQuestion(QuestionModel questionModel, Users userAsked, Users userAnswered) {
		Question questionEntity = new Question();
		BeanUtils.copyProperties(questionModel, questionEntity);
		questionEntity.setAskedId(userAsked);
		if(!userAnswered.getActive().equals(null)) {
			questionEntity.setAnswererId(userAnswered);
		}
		questionRepository.save(questionEntity);
	}

	@Override
	public List<QuestionModel> findByAskedId(Users askedId) {
		List<QuestionModel> listQuestionsModel = new ArrayList<>();
		List<Question> listQuestionsEntity =  questionRepository.findByAskedId(askedId);
		for (Question question : listQuestionsEntity) {
			QuestionModel newQuestionModel = new QuestionModel();
			BeanUtils.copyProperties(question, newQuestionModel);
			listQuestionsModel.add(newQuestionModel);
		}
		return listQuestionsModel;
	}
	
	
}
