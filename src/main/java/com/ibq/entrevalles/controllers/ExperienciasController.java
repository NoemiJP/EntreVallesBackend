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
import com.ibq.entrevalles.model.ImagenExperiencia;
import com.ibq.entrevalles.model.Reserva;
import com.ibq.entrevalles.repository.ExperienciasRepository;
import com.ibq.entrevalles.repository.ImagenExperienciaRepository;

@RestController
public class ExperienciasController {
	
	@Autowired
	private ExperienciasRepository experienciasRepository;
	
	@Autowired
	private ImagenExperienciaRepository imagenRepository;

	@PostMapping("/experiencias")
	public @ResponseBody List<Experiencia> prueba(@RequestBody ExperienciasFiltros experienciasFiltros) {
		List<Experiencia> experiencias = this.experienciasRepository.filter(experienciasFiltros.getLocalizacion(),experienciasFiltros.getAlojamiento(),experienciasFiltros.getEquipamiento());
		for(Experiencia e: experiencias) {
			for(Reserva r: e.getReservas()) {
				r.setExperiencia(null);
			}
		}
		return experiencias;
	}
	
	@GetMapping("/experiencias/{id}")
	public @ResponseBody Experiencia experienciesDetail(@PathVariable("id") Long id) {
		Experiencia experiencia = this.experienciasRepository.findById(id).get();
		for(Reserva r:experiencia.getReservas()) {
			r.setExperiencia(null);
		}
		return experiencia;
	}
	
	@PostMapping("/experiencias/save")
	public @ResponseBody Experiencia save(@RequestBody Experiencia experiencia) {
		Experiencia e = this.experienciasRepository.save(experiencia);
		for(ImagenExperiencia imagen: e.getImagenes()) {
			imagen.setExperiencia(e);
			imagenRepository.save(imagen);
		}
		return e;
	}
	
	@GetMapping("/buscador")
	public @ResponseBody List<Experiencia> experienciasNoReservadas(@RequestParam("fechaInicio") Date fechaInicio,@RequestParam("fechaFin") Date fechaFin){
		return experienciasRepository.findAll();
	}
}
