package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.dto.PermissaoDTO;
import com.passeios_app.backend.dto.RoleRequestDTO;
import com.passeios_app.backend.dto.RoleResponseDTO;
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
	
	public List<RoleResponseDTO> listar(){
		return roleRepository.findAll().stream().map(this::converterResponseRoleDTO).toList();
	}
	
	public RoleResponseDTO buscarPorId(Long id) {
		return roleRepository.findById(id).map(this::converterResponseRoleDTO)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado"));
	}
	
	public RoleResponseDTO salvar(RoleRequestDTO dto) {
		List<Long> ids = dto.getPermissoesIds();
		List<Permissao> permissoes = permissionRepository.findAllById(ids);
				
		if(permissoes.size() != ids.size()) {
			throw new RegraNegocioException("Uma ou mais permissões não existem.");
		}
		
		Role role = new Role();
		role.setNome(dto.getNome());
		role.setDescricao(dto.getDescricao());
		role.setPermissoes(permissoes);
		
		Role salva = roleRepository.save(role);
		
		return converterResponseRoleDTO(salva);
	}
	
	public RoleResponseDTO atualizar(Long id, RoleRequestDTO dto) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado."));
		
		if(dto.getPermissoesIds() == null) {
			throw new RegraNegocioException("As permissões devem ser informadas.");
		}
		
		List<Long> ids = dto.getPermissoesIds();
		List<Permissao> permissoes = permissionRepository.findAllById(ids);
		
		if(permissoes.size() != ids.size()) {
			throw new RegraNegocioException("Uma ou mais permissões não existem.");
		}
		
		role.setNome(dto.getNome());
		role.setDescricao(dto.getDescricao());
		role.setPermissoes(permissoes);
		
		Role salva = roleRepository.save(role);
		return converterResponseRoleDTO(salva);
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
	
	private RoleResponseDTO converterResponseRoleDTO(Role role) {
		RoleResponseDTO dto = new RoleResponseDTO();
		dto.setId(role.getId());
		dto.setNome(role.getNome());
		dto.setDescricao(role.getDescricao());	
		
		List<PermissaoDTO> permissoes = role.getPermissoes().stream().map(this::converterPermissaoResponseDTO).toList();
		
		dto.setPermissoes(permissoes);
		
		return dto;
	}
	
	private PermissaoDTO converterPermissaoResponseDTO(Permissao permissao) {
		PermissaoDTO dto = new PermissaoDTO();
		dto.setId(permissao.getId());
		dto.setCodigo(permissao.getCodigo());
		dto.setDescricao(permissao.getDescricao());
		return dto;
	}
	
}
