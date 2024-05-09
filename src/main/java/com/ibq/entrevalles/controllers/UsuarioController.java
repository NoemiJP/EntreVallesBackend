package com.ibq.entrevalles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.LoginRequest;
import com.ibq.entrevalles.model.Usuario;
import com.ibq.entrevalles.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Usuario>
	login(@RequestBody LoginRequest request) {
		Usuario usuario = this.usuarioRepository.findByEmailAndContrasenya(request.getEmail(), request.getContrasenya());
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.status(401).body(null);
		}
	}
	
	@PostMapping("/registro")
	public @ResponseBody ResponseEntity<Usuario>
	registro(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(this.usuarioRepository.save(usuario));
	}
}
