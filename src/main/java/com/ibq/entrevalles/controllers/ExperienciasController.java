package com.ibq.entrevalles.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.Experiencia;
import com.ibq.entrevalles.model.ExperienciasFiltros;
import com.ibq.entrevalles.repository.ExperienciasRepository;

@RestController
public class ExperienciasController {
	
	@Autowired
	private ExperienciasRepository experienciasRepository;

	@PostMapping("/experiencias")
	public @ResponseBody List<Experiencia> prueba(@RequestBody ExperienciasFiltros experienciasFiltros) {
		return this.experienciasRepository.filter(experienciasFiltros.getLocalizacion());
	}
	
	@GetMapping("/experiencias/{id}")
	public @ResponseBody Experiencia experienciesDetail(@PathVariable("id") Long id) {
		return this.experienciasRepository.findById(id).get();
	}
	
	@PostMapping("/experiencias/save")
	public @ResponseBody Experiencia save(@RequestBody Experiencia experiencia) {
		return this.experienciasRepository.save(experiencia);
	}
	
	@GetMapping("/buscador")
	public @ResponseBody List<Experiencia> experienciasNoReservadas(@RequestParam("fechaInicio") Date fechaInicio,@RequestParam("fechaFin") Date fechaFin){
		return experienciasRepository.findAll();
	}
}
