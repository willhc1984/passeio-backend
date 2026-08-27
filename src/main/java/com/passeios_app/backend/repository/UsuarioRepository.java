package com.passeios_app.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.passeios_app.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);
	// Existe algum usuário com esse e-mail que não seja o usuário que estou editando?
	boolean existsByEmailAndIdNot(String email, Long id);
	
	boolean existsByRoleId(Long roleId);
	
	Optional<Usuario> findByEmail(String email);
	
	@Query("""
	        SELECT u
	        FROM Usuario u
	        JOIN FETCH u.role r
	        LEFT JOIN FETCH r.permissoes
	        WHERE u.email = :email
	    """)
    Optional<Usuario> findByEmailComRoleEPermissoes(@Param("email") String email);
	
}
