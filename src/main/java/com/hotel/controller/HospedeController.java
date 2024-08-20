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

import com.hotel.entities.Hospede;
import com.hotel.service.HospedeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Hospede", description = "API REST DE GERENCIAMENTO DE HOSPEDE")
@RestController
@RequestMapping("api/hospede/")
public class HospedeController {

	private final HospedeService hospedeService;

	@Autowired
	public HospedeController(HospedeService hospedeService) {
		this.hospedeService = hospedeService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um hospede por ID")
	public ResponseEntity<Hospede> buscaHospedeControlId(@PathVariable Long id) {
		Hospede hospede = hospedeService.buscaHospedeId(id);
		if (hospede != null) {
			return ResponseEntity.ok(hospede);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Query Method
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Hospede>>buscaHospedePorNome(@PathVariable String nome){
		List<Hospede> hospede = hospedeService.buscarHospedePorNome(nome);
		return ResponseEntity.ok(hospede);
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta o hospede")
	public ResponseEntity<List<Hospede>> buscaTodosHospedeControl() {
		List<Hospede> Hospede = hospedeService.buscaTodosHospede();
		return ResponseEntity.ok(Hospede);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra o hospede")
	public ResponseEntity<Hospede> salvaHospedeControl(@RequestBody @Valid Hospede hospede) {
		Hospede salvaHospede = hospedeService.salvaHospede(hospede);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaHospede);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera o hospede")
	public ResponseEntity<Hospede> alterarHospedeControl(@PathVariable Long id, @RequestBody @Valid Hospede hospede) {
		Hospede alterarHospede = hospedeService.alterarHospede(id,hospede);
		if (alterarHospede != null) {
			return ResponseEntity.ok(hospede);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui o hospede")
	public ResponseEntity<Hospede>apagaHotelControl(@PathVariable Long id) {
		boolean apagar = hospedeService.apagarHospede(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
