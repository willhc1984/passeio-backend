package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.model.Usuario;
import com.passeios_app.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario existente = buscarPorId(id);
		existente.setNome(usuario.getNome());
		existente.setEmail(usuario.getEmail());
		existente.setSenha(usuario.getSenha());
		existente.setRole(usuario.getRole());
		
		return usuarioRepository.save(existente);
	}
	
	public void excluir(Long id) {
		if(usuarioRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Usuário não encontrado.");
		}
		
		usuarioRepository.deleteById(id);
	}

}
