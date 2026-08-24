package com.passeios_app.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.dto.PermissaoDTO;
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
	
	public List<PermissaoDTO> listar(){
		//return permissionRepository.findAll().stream().map(this::converterDTO).toList();
		List<Permissao> permissoes = permissionRepository.findAll();
		List<PermissaoDTO> dtos = new ArrayList<PermissaoDTO>();
		
		for(Permissao permissao : permissoes) {
			dtos.add(converterDTO(permissao));
		}
		
		return dtos;
	}
	
	public PermissaoDTO buscarPorId(Long id) {
		return permissionRepository.findById(id).map(this::converterDTO)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Permissão não encontrada."));
	}
	
	public Permissao buscarEntidadePorId(Long id) {
		return permissionRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Permissão não encontrada."));
	}
	
	public PermissaoDTO salvar(PermissaoDTO dto) {
		if(permissionRepository.existsByCodigo(dto.getCodigo())) {
			throw new RegraNegocioException("Já existe permissão com esse codigo.");
		}
		
		Permissao permissao = new Permissao();
		permissao.setCodigo(dto.getCodigo());
		permissao.setDescricao(dto.getDescricao());
		
		Permissao salvo = permissionRepository.save(permissao);
		return converterDTO(salvo);
	}
	
	public PermissaoDTO atualizar(Long id, PermissaoDTO dto) {
		Permissao existente = buscarEntidadePorId(id);
		
		if(permissionRepository.existsByCodigoAndIdNot(dto.getCodigo(), id)) {
			throw new RegraNegocioException("Já existe uma permissão com este código.");
		}
		
		existente.setCodigo(dto.getCodigo());
		existente.setDescricao(dto.getDescricao());
		
		Permissao atualizado = permissionRepository.save(existente);
		return converterDTO(atualizado);
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
	
	public PermissaoDTO converterDTO(Permissao permissao) {
		PermissaoDTO dto = new PermissaoDTO();
		dto.setId(permissao.getId());
		dto.setCodigo(permissao.getCodigo());
		dto.setDescricao(permissao.getDescricao());
		return dto;
	}

}
