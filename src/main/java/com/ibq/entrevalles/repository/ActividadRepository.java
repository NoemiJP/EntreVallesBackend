package com.ibq.entrevalles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

	@Query("SELECT a FROM Actividad a " + "LEFT JOIN FETCH a.imagenes ie "
			+ "WHERE ie.id = (SELECT MIN(ie2.id) FROM ImagenActividad ie2 WHERE ie2.actividad.id = a.id) ")
	List<Actividad> findActividades();
}
