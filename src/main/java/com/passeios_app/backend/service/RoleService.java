package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.exception.RegraNegocioException;
import com.passeios_app.backend.model.Permissao;
import com.passeios_app.backend.model.Role;
import com.passeios_app.backend.repository.PermissionRepository;
import com.passeios_app.backend.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	
	public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
	}
	
	public List<Role> listar(){
		return roleRepository.findAll();
	}
	
	public Role buscarPorId(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado"));
	}
	
	public Role salvar(Role role) {
		List<Long> ids = role.getPermissoes().stream().map(Permissao::getId).toList();
		List<Permissao> permissoes = permissionRepository.findAllById(ids);
		
		if(permissoes.size() != ids.size()) {
			throw new RegraNegocioException("Uma ou mais permissões não existem.");
		}
		
		role.setPermissoes(permissoes);
		return roleRepository.save(role);
	}
	
	public Role atualizar(Long id, Role role) {
		Role roleBanco = roleRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrador."));
		
		List<Long> ids = role.getPermissoes().stream().map(Permissao::getId).toList();		
		List<Permissao> permissoes = permissionRepository.findAllById(ids);
		
		if(permissoes.size() != ids.size()) {
			throw new RegraNegocioException("Uma ou mais permissões não existem.");
		}
		
		roleBanco.setNome(role.getNome());
		roleBanco.setDescricao(role.getDescricao());
		roleBanco.setPermissoes(permissoes);
		
		Role existente = buscarPorId(id);
		existente.setNome(role.getNome());
		existente.setDescricao(role.getDescricao());
		existente.setPermissoes(role.getPermissoes());
		
		return roleRepository.save(roleBanco);
	}
	
	public void excluir(Long id) {
		if(!roleRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Papel não encontrado.");
		}
		
		roleRepository.deleteById(id);
	}
	
}
