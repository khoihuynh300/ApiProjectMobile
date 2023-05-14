package com.example.api.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Inn")
public class Inn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long innId;
	
	@Column(name = "Size")
	private int size;
	
	@Column(name = "PriceWater")
	private Double priceWater;
	
	@Column(name = "PriceElec")
	private Double priceELec;
	
	@Column(name = "Address")
	@Nationalized 
	private String address;
	
	@Column(name = "Price")
	private Double price;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "Describe")
	@Nationalized 
	private String describe;
	
	@Column(name = "isDeleted")
	private Boolean isDeleted = false;
	
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
	@JoinColumn(name = "ProposedById")
	private Users proposedById;

	@Column(name = "IsConfirmed")
	private Boolean isConfirmed = false;
	
	@ManyToOne
	@JoinColumn(name = "ConfirmedById")
	private Users confirmedById;
}
