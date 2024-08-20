package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
	

}
