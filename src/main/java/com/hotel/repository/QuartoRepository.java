package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.entities.Quarto;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
	
	        //Query Method
			List<Quarto>findByNumero(int numero);

}
