package com.ibq.entrevalles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.ReservaAct;

@Repository
public interface ReservaActRepository extends JpaRepository<ReservaAct, Long>{

}
