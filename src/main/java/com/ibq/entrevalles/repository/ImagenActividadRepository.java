package com.ibq.entrevalles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.ImagenActividad;

@Repository
public interface ImagenActividadRepository extends JpaRepository<ImagenActividad, Long>{

}
