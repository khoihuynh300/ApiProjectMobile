package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Inn;
import com.example.api.entity.Message;
import com.example.api.model.ImageModel;
import com.example.api.model.MessageModel;
import com.example.api.repository.ImageInnRepository;
import com.example.api.repository.MessageRepository;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
    MessageRepository messageRepository;
	
	@Autowired
	IUsersService iUsersService;
	
	@Autowired
	IInnService iInnService;

	@Override
	public <S extends Message> S save(S entity) {
		return messageRepository.save(entity);
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
//	@Override
//	public void createMessageOfInn(MessageModel messageModel) {
//		Message message = new Message();
//		message.setMessage(messageModel.getMessage());
//		message.setImage(messageModel.getImage());
//		message.setCreatedAt(messageModel.getCreatedAt());
//		message.setUpdatedAt(messageModel.getUpdatedAt());
//		message.setUserId(iUsersService.findById(messageModel.getUserId()).get());
//		
//		Inn inn = new Inn();
//		inn.setInnId(messageModel.getInnId());
//		message.setInnId(inn);
//		
//		messageRepository.save(message);
//	}
}
