package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.model.Role;
import com.passeios_app.backend.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> listar(){
		return roleRepository.findAll();
	}
	
	public Role buscarPorId(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado"));
	}
	
	public Role salvar(Role role) {
		return roleRepository.save(role);
	}
	
	public Role atualizar(Long id, Role role) {
		Role existente = buscarPorId(id);
		existente.setNome(role.getNome());
		existente.setDescricao(role.getDescricao());
		existente.setPermissoes(role.getPermissoes());
		
		return roleRepository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!roleRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Papel não encontrado.");
		}
		
		roleRepository.deleteById(id);
	}
	
}
