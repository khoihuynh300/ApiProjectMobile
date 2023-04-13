package com.example.api.service;

import java.util.List;

import com.example.api.model.MessageModel;

public interface MessageService {
	public List<MessageModel> getAllMessageByInnId(Long innId);
	public void createMessageOfInn(MessageModel messageModel);
}
