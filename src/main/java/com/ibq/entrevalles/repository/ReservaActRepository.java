package com.ibq.entrevalles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.ReservaAct;

@Repository
public interface ReservaActRepository extends JpaRepository<ReservaAct, Long>{

	public List<ReservaAct> findByUsuarioId(Long usuarioId);
}
