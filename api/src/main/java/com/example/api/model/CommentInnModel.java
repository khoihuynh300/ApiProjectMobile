package com.example.api.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentInnModel {
	private Long commentInnId;
	private String content;
	private String image;
	private Date createdAt;
	private Date updatedAt;
	private String username;
	private String avatar;
	private Long userId;
	private Long innId;
}
