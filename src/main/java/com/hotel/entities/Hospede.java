package com.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hospede")
public class Hospede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	
	@Column(name = "nome",nullable = false, length = 100)
	private String nome;
	
	@Column(name = "telefone",nullable = false, length = 100)
	private String telefone;
	
	@Column(name = "email",nullable = false, length = 100)
	private String email;
	

}

