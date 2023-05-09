package com.example.api.service;

import java.util.List;

import com.example.api.entity.Message;
import com.example.api.model.MessageModel;

public interface MessageService {

	<S extends Message> S save(S entity);
//	public List<MessageModel> getAllMessageByInnId(Long innId);
//	public void createMessageOfInn(MessageModel messageModel);
}
