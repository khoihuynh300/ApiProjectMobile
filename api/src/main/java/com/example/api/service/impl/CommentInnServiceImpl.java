package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.CommentInn;
import com.example.api.entity.Inn;
import com.example.api.entity.Message;
import com.example.api.model.CommentInnModel;
import com.example.api.model.MessageModel;
import com.example.api.repository.CommentInnRepository;
import com.example.api.repository.MessageRepository;
import com.example.api.service.ICommentInnService;
import com.example.api.service.IInnService;
import com.example.api.service.IUsersService;
import com.example.api.service.MessageService;

@Service
public class CommentInnServiceImpl implements ICommentInnService{
	@Autowired
    CommentInnRepository commentInnRepository;
	
	@Autowired
	IUsersService iUsersService;
	
	@Autowired
	IInnService iInnService;

	@Override
	public List<CommentInnModel> getAllCommentByInnId(Long innId) {
		Inn inn = new Inn();
		inn.setInnId(innId);
		
		List<CommentInn> commentInns = commentInnRepository.findByInnId(inn);
		
		List<CommentInnModel> commentInnModels = new ArrayList<>();
		for (CommentInn commentInn : commentInns) {
			CommentInnModel commentInnModel = new CommentInnModel();
			BeanUtils.copyProperties(commentInn, commentInnModel);	
			commentInnModel.setUsername(commentInn.getUserId().getFullname());
			commentInnModel.setAvatar(commentInn.getUserId().getAvatar());
			commentInnModels.add(commentInnModel);
		}
		
        return commentInnModels;
    }
	
	@Override
	public void createCommentOfInn(CommentInnModel commentInnModel) {
		CommentInn message = new CommentInn();
		message.setContent(commentInnModel.getContent());
		message.setImage(commentInnModel.getImage());
		message.setUserId(iUsersService.findById(commentInnModel.getUserId()).get());
		
		Inn inn = new Inn();
		inn.setInnId(commentInnModel.getInnId());
		message.setInnId(inn);
		
		commentInnRepository.save(message);
	}
}
