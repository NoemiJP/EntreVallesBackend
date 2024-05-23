package com.ibq.entrevalles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.Localidad;
import com.ibq.entrevalles.repository.LocalidadRepository;

@RestController
public class LocalidadController {

    private final LocalidadRepository localidadRepository;

    @Autowired
    public LocalidadController(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @GetMapping("/localidades")
    public List<Localidad> obtenerLocalidades() {
        return localidadRepository.findAll(); 
    }
}