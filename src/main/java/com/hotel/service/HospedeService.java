package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entities.Hospede;
import com.hotel.repository.HospedeRepository;

@Service
public class HospedeService {


	private final HospedeRepository hospedeRepository;

	@Autowired

	public HospedeService (HospedeRepository hospedeRepository) {
		this.hospedeRepository = hospedeRepository;
	}

	public List<Hospede> buscaTodosHospede(){
		return hospedeRepository.findAll();
	}
	//Query Method
	public List<Hospede> buscarHospedePorNome(String nome){
		return hospedeRepository.findByNome(nome);
	}
	public Hospede buscaHospedeId(Long id) {
		Optional <Hospede> Hospede = hospedeRepository.findById(id);
		return Hospede.orElse(null);
	}
	public Hospede salvaHospede(Hospede hospede){
		return hospedeRepository.save(hospede);
	}
	public Hospede alterarHospede(Long id, Hospede alterarHospede) {
		Optional <Hospede> existeHospede = hospedeRepository.findById(id);
		if (existeHospede.isPresent()) {
			alterarHospede.setId(id);
			return hospedeRepository.save(alterarHospede);
		}
		return null;
	}
	public boolean apagarHospede (Long id) {
		Optional <Hospede> existeHospede = hospedeRepository.findById(id);
		if (existeHospede.isPresent()) {
			hospedeRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

