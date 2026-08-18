package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.model.Permissao;
import com.passeios_app.backend.repository.PermissionRepository;

@Service
public class PermissionService {
	
	private final PermissionRepository permissionRepository;
	
	public PermissionService(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}
	
	public List<Permissao> listar(){
		return permissionRepository.findAll();
	}
	
	public Permissao buscarPorId(Long id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Permissão não encontrada"));
	}
	
	public Permissao salvar(Permissao permissao) {
		return permissionRepository.save(permissao);
	}
	
	public Permissao atualizar(Long id, Permissao permissao) {
		Permissao existente = buscarPorId(id);
		existente.setCodigo(permissao.getCodigo());
		existente.setDescricao(permissao.getDescricao());
		
		return permissionRepository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!permissionRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Permissão não encontrada.");
		}
		
		permissionRepository.deleteById(id);
	}

}
