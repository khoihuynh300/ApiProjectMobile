package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Message;
import com.example.api.entity.Question;
import com.example.api.model.MessageModel;
import com.example.api.repository.MessageRepository;
import com.example.api.repository.QuestionRepository;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
    MessageRepository messageRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	IUsersService iUsersService;
	
	@Autowired
	IInnService iInnService;

	@Override
	public <S extends Message> S save(S entity) {
		return messageRepository.save(entity);
	}
	
	public List<MessageModel> getAllMessageByQuestionId(Long questionId){
		Optional<Question> question = questionRepository.findById(questionId);
		List<MessageModel> messagesModel = new ArrayList<>();
		if(question.isPresent()) {
			List<Message> messagesEntity = messageRepository.findAllByQuestionId(question.get());
			if(messagesEntity.size()>0) {
				for(Message messageEntity : messagesEntity) {
					MessageModel newMessageModel = new MessageModel();
					BeanUtils.copyProperties(messageEntity, newMessageModel);
					newMessageModel.setUsername(messageEntity.getUserId().getFullname());
					messagesModel.add(newMessageModel);
				}
			}
		}
		return messagesModel;
	}
	

//	@Override
//    public List<MessageModel> getAllMessageByInnId(Long innId) {
//		Inn inn = new Inn();
//		inn.setInnId(innId);
//		
//		List<Message> messages = messageRepository.findByInnId(inn);
//		
//		List<MessageModel> messageModels = new ArrayList<>();
//		for (Message message : messages) {
//			MessageModel messageModel = new MessageModel();
//			BeanUtils.copyProperties(message, messageModel);
//			messageModel.setUsername(message.getUserId().getFullname());
//			
//			messageModels.add(messageModel);
//		}
//		
//        return messageModels;
//    }
//	
	@Override
	public void addMessage(Message message) {
		messageRepository.save(message);
	}
}
