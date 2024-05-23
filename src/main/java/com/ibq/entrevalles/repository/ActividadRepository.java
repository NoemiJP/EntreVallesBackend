package com.ibq.entrevalles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.Actividad;

@Repository
	public interface ActividadRepository  extends JpaRepository<Actividad, Long>{
	}


