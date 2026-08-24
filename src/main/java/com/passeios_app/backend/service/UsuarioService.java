package com.passeios_app.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.dto.UsuarioRequestDTO;
import com.passeios_app.backend.dto.UsuarioResponseDTO;
import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.exception.RegraNegocioException;
import com.passeios_app.backend.model.Role;
import com.passeios_app.backend.model.Usuario;
import com.passeios_app.backend.repository.RoleRepository;
import com.passeios_app.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final RoleRepository roleRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.roleRepository = roleRepository;
	}
	
	public List<UsuarioResponseDTO> listar(){	
		
		//return usuarioRepository.findAll().stream().map(this::converterResponseDTO).toList();
		
		List<Usuario> usuarios = usuarioRepository.findAll();		
		List<UsuarioResponseDTO> dtos = new ArrayList<>();	
		
		for (Usuario usuario : usuarios) {
			dtos.add(converterResponseDTO(usuario));
		}		
		
		return dtos;
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
	}
	
	public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
		if(dto.getRoleId() == null) {
			throw new RegraNegocioException("Usuário deve possuir um papel.");
		}
		
		Role role = roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado."));
		
		if(usuarioRepository.existsByEmail(dto.getEmail())) {
			throw new RegraNegocioException("E-mail já está em uso.");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setRole(role);
		
		Usuario salvo = usuarioRepository.save(usuario);
		return converterResponseDTO(salvo);
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		if(usuario.getRole() == null || usuario.getRole().getId() == null) {
			throw new RegraNegocioException("Usuário deve possui um papel");	
		}
		
		Role role = roleRepository.findById(usuario.getRole().getId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Papel não encontrado."));
		
		Usuario existente = buscarPorId(id);
		
		if(usuarioRepository.existsByEmailAndIdNot(usuario.getEmail(), id)) {
			throw new RegraNegocioException("Já existe um usuário com este e-mail.");
		}
		
		existente.setNome(usuario.getNome());
		existente.setEmail(usuario.getEmail());
		existente.setSenha(usuario.getSenha());
		existente.setRole(usuario.getRole());
		
		return usuarioRepository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!usuarioRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Usuário não encontrado.");
		}
		
		usuarioRepository.deleteById(id);
	}
	
	private UsuarioResponseDTO converterResponseDTO(Usuario usuario) {
		UsuarioResponseDTO dto = new UsuarioResponseDTO();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
		
		if(usuario.getRole() != null) {
			dto.setRoleId(usuario.getRole().getId());
		}
		
		return dto;
	}
	
}
