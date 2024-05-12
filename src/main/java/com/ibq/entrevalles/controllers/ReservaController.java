package com.ibq.entrevalles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.Experiencia;
import com.ibq.entrevalles.model.Reserva;
import com.ibq.entrevalles.model.ReservaRequest;
import com.ibq.entrevalles.model.Usuario;
import com.ibq.entrevalles.repository.ExperienciasRepository;
import com.ibq.entrevalles.repository.ReservaRepository;
import com.ibq.entrevalles.repository.UsuarioRepository;

@RestController
public class ReservaController {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ExperienciasRepository experienciaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/reservar")
	public @ResponseBody Reserva reservar(@RequestBody ReservaRequest reservaRequest) {
		Reserva reserva = new Reserva();
		Experiencia experiencia = this.experienciaRepository.findById(reservaRequest.getExperiencia()).get();
		Usuario usuario = this.usuarioRepository.findById(reservaRequest.getUsuario()).get();
		reserva.setExperiencia(experiencia);
		reserva.setFechaFin(reservaRequest.getFechaFin());
		reserva.setFechaInicio(reservaRequest.getFechaInicio());
		reserva.setHuespedesTotales(reservaRequest.getHuespedesTotales());
		reserva.setPrecioTotal(reservaRequest.getPrecioTotal());
		reserva.setPrecioTotal(reservaRequest.getPrecioTotal());
		reserva.setUsuario(usuario);
		Reserva reservaSave = this.reservaRepository.save(reserva);
		reservaSave.getExperiencia().setReservas(null);
		return reservaSave;
	}
	
	@GetMapping("/reserva/{id}")
	public @ResponseBody Reserva reservar(@PathVariable Long id) {
		Reserva r = this.reservaRepository.findById(id).get();
		r.getExperiencia().setReservas(null);
		return r;
	}
}
