package com.passeios_app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passeios_app.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	boolean existsByPermissoesId(Long permissaoId);

}
