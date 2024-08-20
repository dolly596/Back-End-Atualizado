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
@Table(name = "quarto")
public class Quarto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "capacidade",nullable = false, length = 100)
	private String capacidade;
	
	@Column(name = "preco",nullable = false, length = 100)
	private Double preco;
	
	@Column(name = "andar",nullable = false, length = 100)
	private int andar;
	
	@Column(name = "numero",nullable = false, length = 100)
	private int numero;

}


