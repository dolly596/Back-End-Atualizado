package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Reserva;
import com.hotel.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Reserva", description = "API REST DE GERENCIAMENTO DE RESERVA")
@RestController
@RequestMapping("api/reserva")
public class ReservaController {

	private final ReservaService reservaService;

	@Autowired
	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza uma reserva por ID")
	public ResponseEntity<Reserva> buscaReservaControlId(@PathVariable Long id) {
		Reserva reserva = reservaService.buscaReservaId(id);
		if (reserva != null) {
			return ResponseEntity.ok(reserva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping
	@Operation(summary = "Apresenta uma reserva")
	public ResponseEntity<List<Reserva>> buscaTodosHotelControl() {
		List<Reserva> Reserva = reservaService.buscaTodosReserva();
		return ResponseEntity.ok(Reserva);
	}

	@PostMapping
	@Operation(summary = "Cadastra uma reserva")
	public ResponseEntity<Reserva> salvaReservaControl(@RequestBody @Valid Reserva  reserva) {
		Reserva salvaReserva =  reservaService.salvaReserva( reserva);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaReserva);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera uma reserva")
	public ResponseEntity<Reserva> alterarReservaControl(@PathVariable Long id, @RequestBody @Valid Reserva  reserva) {
		Reserva alterarReserva =  reservaService.alterarReserva(id, reserva);
		if (alterarReserva != null) {
			return ResponseEntity.ok(reserva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui uma reserva")
	public ResponseEntity<Reserva>apagaReservaControl(@PathVariable Long id) {
		boolean apagar =  reservaService.apagarReserva(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
