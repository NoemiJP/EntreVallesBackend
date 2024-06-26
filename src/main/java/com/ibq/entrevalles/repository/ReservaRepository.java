package com.ibq.entrevalles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	public List<Reserva> findByUsuarioId(Long usuarioId);
}
