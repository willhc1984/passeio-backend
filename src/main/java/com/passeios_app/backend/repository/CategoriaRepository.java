package com.passeios_app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passeios_app.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	

}
