package com.passeios_app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passeios_app.backend.model.Permissao;

public interface PermissionRepository extends JpaRepository<Permissao, Long>{
	
	boolean existsByCodigo(String codigo);

}
