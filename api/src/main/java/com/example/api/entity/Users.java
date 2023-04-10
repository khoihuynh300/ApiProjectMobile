package com.example.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "Email", unique=true)
	private String email;
	
	@Column(name = "Password")
	private String password;

	@Column(name = "Fullname")
	private String fullname;
	
	@Column(name = "Gender")
	@Enumerated(EnumType.STRING)
	private Gender Gender;

	@Column(name = "Avatar")
	private String avatar;

	@Column(name = "Role")
	private String role = "sinhvien";

//	@Column(name = "Ban")
//	private Boolean ban = false;

	@Column(name = "Active")
	private Boolean active = true;
	

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
	
	@OneToMany(mappedBy = "proposedById", cascade = CascadeType.ALL)
	private Set<Inn> innProposed;
	
	@OneToMany(mappedBy = "confirmedById", cascade = CascadeType.ALL)
	private Set<Inn> innConfirmed;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private Set<Notification> notifications;
	
	@OneToMany(mappedBy = "askedId", cascade = CascadeType.ALL)
	private Set<Question> questionsAsk;
	
	@OneToMany(mappedBy = "answererId", cascade = CascadeType.ALL)
	private Set<Question> questionsAnswer;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private Set<Message> message;
	
	public enum Gender {
	    MALE,
	    FEMALE
	}
	
}
