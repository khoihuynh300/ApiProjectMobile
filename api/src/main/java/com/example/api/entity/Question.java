package com.example.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Question")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "ViewCount")
	private Double view;
	
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}

	@ManyToOne
	@JoinColumn(name = "AskedId")
	private Users askedId;
	
	@ManyToOne
	@JoinColumn(name = "AnswererId")
	private Users answererId;
	
	@OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL)
	private Set<Message> message;
}
