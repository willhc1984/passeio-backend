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

import com.passeios_app.backend.dto.LugarRequestDTO;
import com.passeios_app.backend.dto.LugarResponseDTO;
import com.passeios_app.backend.model.Lugar;
import com.passeios_app.backend.service.LugarService;

@RestController
@RequestMapping("/lugares")
public class LugarController {
	
	private final LugarService service;
	
	public LugarController(LugarService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAuthority('lugar.visualizar')")
	@GetMapping
	public List<LugarResponseDTO> listar(){
		return service.listar();
	}
	
	@PreAuthorize("hasAuthority('lugar.visualizar')")
	@GetMapping("/{id}")
	public LugarResponseDTO buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PreAuthorize("hasAuthority('lugar.criar')")
	@PostMapping
	public LugarResponseDTO salvar(@RequestBody LugarRequestDTO dto) {
		return service.salvar(dto);
	}
	
	@PreAuthorize("hasAuthority('lugar.editar')")
	@PutMapping("/{id}")
	public LugarResponseDTO atualizar(@PathVariable Long id, @RequestBody LugarRequestDTO dto) {
		return service.atualizar(id, dto);
	}
	
	@PreAuthorize("hasAuthority('lugar.excluir')")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
}
