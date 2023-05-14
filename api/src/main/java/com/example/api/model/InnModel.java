package com.example.api.model;

import java.util.Date;
import java.util.List;

import com.example.api.entity.ImageInn;
import com.example.api.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InnModel {
	private Long innId;
	private int size;
	private Double priceWater;
	private Double priceELec;
	private String address;
	private Double price;
	private String phoneNumber;
	private String describe;
	private Date createdAt;
	private Date updatedAt;
	private String proposed;
	private Long proposedId;
	private Boolean isConfirmed = false;
	private Long confirmedById;
	private ImageModel mainImage;
	private List<ImageModel> images;
	private Boolean isDeleted = false;
}
