package com.ibq.entrevalles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.ImagenExperiencia;

@Repository
public interface ImagenExperienciaRepository extends JpaRepository<ImagenExperiencia, Long>{
}
