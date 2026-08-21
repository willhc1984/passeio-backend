package com.passeios_app.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.dto.UsuarioRequestDTO;
import com.passeios_app.backend.dto.UsuarioResponseDTO;
import com.passeios_app.backend.model.Usuario;
import com.passeios_app.backend.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioService.listar();
	}
	
	@GetMapping("/{id}")
	public Usuario buscarPoId(@PathVariable Long id) {
		return usuarioService.buscarPorId(id);
	}
	
	@PostMapping
	public UsuarioResponseDTO salvar(@RequestBody UsuarioRequestDTO usuario) {
		return usuarioService.salvar(usuario);
	}
	
	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		return usuarioService.atualizar(id, usuario);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		usuarioService.excluir(id);
	}

}
