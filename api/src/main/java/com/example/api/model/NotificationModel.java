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
public class NotificationModel {
	private Long notificationId;
	private String notificationContent;
	private String notificationLink;
	private Boolean isViewed;
	private Date createdAt;
	private Date updatedAt;
}
