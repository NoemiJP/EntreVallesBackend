package com.ibq.entrevalles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibq.entrevalles.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByEmailAndContrasenya(String email,String contrasenya);
	
	public Usuario findByEmail(String email);
}
