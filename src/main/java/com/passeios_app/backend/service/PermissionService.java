package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.exception.RegraNegocioException;
import com.passeios_app.backend.model.Permissao;
import com.passeios_app.backend.repository.PermissionRepository;
import com.passeios_app.backend.repository.RoleRepository;

@Service
public class PermissionService {
	
	private final PermissionRepository permissionRepository;
	private final RoleRepository roleRepository;
	
	public PermissionService(PermissionRepository permissionRepository, RoleRepository roleRepository) {
		this.permissionRepository = permissionRepository;
		this.roleRepository = roleRepository;
	}
	
	public List<Permissao> listar(){
		return permissionRepository.findAll();
	}
	
	public Permissao buscarPorId(Long id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Permissão não encontrada."));
	}
	
	public Permissao salvar(Permissao permissao) {
		if(permissionRepository.existsByCodigo(permissao.getCodigo())) {
			throw new RegraNegocioException("Já existe permissão com esse codigo.");
		}
		return permissionRepository.save(permissao);
	}
	
	public Permissao atualizar(Long id, Permissao permissao) {
		Permissao existente = buscarPorId(id);
		
		if(permissionRepository.existsByCodigoAndIdNot(permissao.getCodigo(), id)) {
			throw new RegraNegocioException("Já existe uma permissão com este código.");
		}
		
		existente.setCodigo(permissao.getCodigo());
		existente.setDescricao(permissao.getDescricao());
		
		return permissionRepository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!permissionRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Permissão não encontrada.");
		}
		
		if(roleRepository.existsByPermissoesId(id)) {
			throw new RegraNegocioException("Não é possivel excluir a permissão pois está associada a um Papel.");
		}
		
		permissionRepository.deleteById(id);
	}

}
