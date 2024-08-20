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

import com.hotel.entities.Quarto;
import com.hotel.service.QuartoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Quarto", description = "API REST DE GERENCIAMENTO DE QUARTO")
@RestController
@RequestMapping("api/quarto/")
public class QuartoController {

	private final QuartoService quartoService;

	@Autowired
	public QuartoController(QuartoService quartoService) {
		this.quartoService = quartoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um quarto por ID")
	public ResponseEntity<Quarto> buscaQuartoControlId(@PathVariable Long id) {
		Quarto quarto = quartoService.buscaQuartoId(id);
		if (quarto != null) {
			return ResponseEntity.ok(quarto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Query Method
	@GetMapping("/numero/{numero}")
	public ResponseEntity<List<Quarto>>buscaQuartoPorNumero(@PathVariable int numero){
		List<Quarto> quarto = quartoService.buscarQuartoPorNumero(numero);
		return ResponseEntity.ok(quarto);
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta o quarto")
	public ResponseEntity<List<Quarto>> buscaTodosQuartoControl() {
		List<Quarto> Quarto = quartoService.buscaTodosQuarto();
		return ResponseEntity.ok(Quarto);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra o quarto")
	public ResponseEntity<Quarto> salvaQuartoControl(@RequestBody @Valid Quarto  quarto) {
		Quarto salvaQuarto =  quartoService.salvaQuarto( quarto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaQuarto);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera o quarto")
	public ResponseEntity<Quarto> alterarQuartoControl(@PathVariable Long id, @RequestBody @Valid Quarto  quarto) {
		Quarto alterarQuarto =  quartoService.alterarQuarto(id, quarto);
		if (alterarQuarto != null) {
			return ResponseEntity.ok(quarto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um quarto")
	public ResponseEntity<Quarto>apagaQuartoControl(@PathVariable Long id) {
		boolean apagar =  quartoService.apagarQuarto(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
