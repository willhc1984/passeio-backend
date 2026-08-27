package com.passeios_app.backend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.dto.CategoriaRequestDTO;
import com.passeios_app.backend.dto.CategoriaResponseDTO;
import com.passeios_app.backend.model.Categoria;
import com.passeios_app.backend.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private final CategoriaService service;
	
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAuthority('categoria.visualizar')")
	@GetMapping
	public List<CategoriaResponseDTO> listar(){
		return service.listar();
	}
	
	@PreAuthorize("hasAuthority('categoria.visualizar')")
	@GetMapping("/{id}")
	public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PreAuthorize("hasAuthority('categoria.criar')")
	@PostMapping
	public CategoriaResponseDTO salvar(@RequestBody CategoriaRequestDTO dto) {
		return service.salvar(dto);
	}
	
	@PreAuthorize("hasAuthority('categoria.editar')")
	@PutMapping("/{id}")
	public CategoriaResponseDTO atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto) {
		return service.atualizar(id, dto);
	}
	
	@PreAuthorize("hasAuthority('categoria.excluir')")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
}
