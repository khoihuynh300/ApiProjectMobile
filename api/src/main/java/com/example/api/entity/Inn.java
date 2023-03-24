package com.example.api.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Price")
	private Double price;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "Describe")
	private String describe;

	@ManyToOne
	@JoinColumn(name = "ProposedById")
	private Users proposedById;

	@Column(name = "IsConfirmed")
	private Boolean isConfirmed;
	
	@ManyToOne
	@JoinColumn(name = "ConfirmedById")
	private Users confirmedById;
}
