package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.exception.RegraNegocioException;
import com.passeios_app.backend.model.Permissao;
import com.passeios_app.backend.model.Role;
import com.passeios_app.backend.repository.PermissionRepository;
import com.passeios_app.backend.repository.RoleRepository;
import com.passeios_app.backend.repository.UsuarioRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	private final UsuarioRepository usuarioRepository;
	
	public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository, UsuarioRepository usuarioRepository) {
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
		this.usuarioRepository = usuarioRepository;
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
		System.out.println("Id's encontradas: " + ids);
		List<Permissao> permissoes = permissionRepository.findAllById(ids);
		System.out.println("Permissões: " + permissoes);
		
		if(permissoes.size() != ids.size()) {
			throw new RegraNegocioException("Uma ou mais permissões não existem.");
		}
		
		role.setPermissoes(permissoes);
		return roleRepository.save(role);
	}
	
	public Role atualizar(Long id, Role role) {
		Role roleBanco = roleRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado."));
		
		List<Long> ids = role.getPermissoes().stream().map(Permissao::getId).toList();	
		List<Permissao> permissoes = permissionRepository.findAllById(ids);
		
		if(permissoes.size() != ids.size()) {
			throw new RegraNegocioException("Uma ou mais permissões não existem.");
		}
		
		roleBanco.setNome(role.getNome());
		roleBanco.setDescricao(role.getDescricao());
		roleBanco.setPermissoes(permissoes);
	
		return roleRepository.save(roleBanco);
	}
	
	public void excluir(Long id) {
		if(!roleRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Papel não encontrado.");
		}
		
		if(usuarioRepository.existsByRoleId(id)) {
			throw new RegraNegocioException("Não é possivel excluir o Papel pois existem usuários associados.");
		}
		
		roleRepository.deleteById(id);
	}
	
}
