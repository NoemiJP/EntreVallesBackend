package com.ibq.entrevalles.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.Actividad;
import com.ibq.entrevalles.model.ImagenActividad;
import com.ibq.entrevalles.model.ReservaAct;
import com.ibq.entrevalles.model.ReservaActRequest;
import com.ibq.entrevalles.model.Usuario;
import com.ibq.entrevalles.repository.ActividadRepository;
import com.ibq.entrevalles.repository.ImagenActividadRepository;
import com.ibq.entrevalles.repository.ReservaActRepository;
import com.ibq.entrevalles.repository.UsuarioRepository;

@RestController
public class ActividadController {
    
    @Autowired
    private ActividadRepository actividadRepository;
    
    @Autowired
    private ReservaActRepository reservaActRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ImagenActividadRepository imagenRepository;
    
    @PostMapping("/actividad")
    public ResponseEntity<Actividad> guardarActividad(@RequestBody Actividad actividad) {
        Actividad savedActividad = actividadRepository.save(actividad);
        return ResponseEntity.ok(savedActividad);
    }
    
    @GetMapping("/actividad/{id}")
    public ResponseEntity<Actividad> obtenerActividadPorId(@PathVariable("id") Long id) {
        Optional<Actividad> optionalActividad = actividadRepository.findById(id);
        if (optionalActividad.isPresent()) {
            Actividad actividad = optionalActividad.get();
            List<ReservaAct> reservas = actividad.getReservas();
            for(ReservaAct reserva: reservas) {
            	reserva.setActividad(null);
            }
            return ResponseEntity.ok(actividad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/reservaAct")
    public @ResponseBody ReservaAct reservarActividad(@RequestBody ReservaActRequest reservaRequest) {
    	ReservaAct reserva = new ReservaAct();
		Actividad experiencia = this.actividadRepository.findById(reservaRequest.getActividad()).get();
		Usuario usuario = this.usuarioRepository.findById(reservaRequest.getUsuario()).get();
		reserva.setActividad(experiencia);
		reserva.setFechaFin(reservaRequest.getFechaFin());
		reserva.setFechaInicio(reservaRequest.getFechaInicio());
		reserva.setPersonas(reservaRequest.getHuespedesTotales());
		reserva.setPrecio(reservaRequest.getPrecioTotal());
		reserva.setUsuario(usuario);
		ReservaAct reservaSave = this.reservaActRepository.save(reserva);
		reservaSave.getActividad().setReservas(null);
		return reservaSave;
    }
    
    @GetMapping("/reservaAct/{id}")
    public @ResponseBody ReservaAct reserva(@PathVariable Long id) {
    	ReservaAct reserva = reservaActRepository.findById(id).get();
    	reserva.getActividad().setReservas(null);
    	return reserva;
}
    
    @PostMapping("/actividades/save")
	public @ResponseBody Actividad save(@RequestBody Actividad actividad) {
    	Actividad e = this.actividadRepository.save(actividad);
		for(ImagenActividad imagen: e.getImagenes()) {
			imagen.setActividad(e);
			imagenRepository.save(imagen);
		}
		return e;
	}
    
    @GetMapping("/activities")
    public ResponseEntity<List<Actividad>> actividades(){
    	List<Actividad> actividades = actividadRepository.findActividades();
    	for(Actividad act: actividades) {
    		act.setReservas(null);
    	}
    	return ResponseEntity.ok(actividades);
    }
}
