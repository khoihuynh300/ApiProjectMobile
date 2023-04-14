package com.example.api.model;

import java.util.Date;
import java.util.List;

import com.example.api.entity.ImageInn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {
	private Long imageInnId;
	private String image;
}
