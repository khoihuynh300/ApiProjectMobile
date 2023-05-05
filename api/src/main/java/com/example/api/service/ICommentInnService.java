package com.example.api.service;

import java.util.List;

import com.example.api.model.CommentInnModel;

public interface ICommentInnService {
	public List<CommentInnModel> getAllCommentByInnId(Long innId);
	public void createCommentOfInn(CommentInnModel commentInnModel);
}
