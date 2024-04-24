package com.ibq.entrevalles.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.Experiencia;

@Repository
public interface ExperienciasRepository extends JpaRepository<Experiencia, Long>{

	@Query(value = "SELECT e FROM Experiencia e WHERE e NOT IN " +
	           "(SELECT r.experiencia FROM Reserva r WHERE r.fechaInicio BETWEEN :fechaInicio AND :fechaFin AND r.fechaFin BETWEEN :fechaInicio AND :fechaFin)")
	public List<Experiencia> filterByBookDates(@Param("fechaInicio")Date fechaInicio,@Param("fechaFin")Date fechaFin);
	
	@Query(value = "SELECT e FROM Experiencia e WHERE (:localizacion IS NULL OR e.localizacion IN (:localizacion))")
	public List<Experiencia> filter(@Param("localizacion")List<String> localizacion);
}
