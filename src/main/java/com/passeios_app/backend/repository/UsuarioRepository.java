package com.passeios_app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passeios_app.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
