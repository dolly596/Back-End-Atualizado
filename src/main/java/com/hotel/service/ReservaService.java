package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entities.Reserva;
import com.hotel.repository.ReservaRepository;

@Service
public class ReservaService {


	private final ReservaRepository reservaRepository;

	@Autowired

	public ReservaService (ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	public List<Reserva> buscaTodosReserva(){
		return reservaRepository.findAll();
	}
	public Reserva buscaReservaId(Long id) {
		Optional <Reserva> Reserva = reservaRepository.findById(id);
		return Reserva.orElse(null);
	}
	public Reserva salvaReserva(Reserva reserva){
		return reservaRepository.save(reserva);
	}
	public Reserva alterarReserva(Long id, Reserva alterarReserva) {
		Optional <Reserva> existeReserva = reservaRepository.findById(id);
		if (existeReserva.isPresent()) {
			alterarReserva.setId(id);
			return reservaRepository.save(alterarReserva);
		}
		return null;
	}
	public boolean apagarReserva (Long id) {
		Optional <Reserva> existeReserva = reservaRepository.findById(id);
		if (existeReserva.isPresent()) {
			reservaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

