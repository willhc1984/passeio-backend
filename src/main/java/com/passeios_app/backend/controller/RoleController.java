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

import com.passeios_app.backend.dto.RoleRequestDTO;
import com.passeios_app.backend.dto.RoleResponseDTO;
import com.passeios_app.backend.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PreAuthorize("hasAuthority('papel.visualizar')")
	@GetMapping
	public List<RoleResponseDTO> listar(){
		return roleService.listar();
	}
	
	@PreAuthorize("hasAuthority('papel.visualizar')")
	@GetMapping("/{id}")
	public RoleResponseDTO buscarPoId(@PathVariable Long id) {
		return roleService.buscarPorId(id);
	}
	
	@PreAuthorize("hasAuthority('papel.criar')")
	@PostMapping
	public RoleResponseDTO salvar(@RequestBody RoleRequestDTO dto) {
		return roleService.salvar(dto);
	}
	
	@PreAuthorize("hasAuthority('papel.editar')")
	@PutMapping("/{id}")
	public RoleResponseDTO atualizar(@PathVariable Long id, @RequestBody RoleRequestDTO dto) {
		return roleService.atualizar(id, dto);
	}
	
	@PreAuthorize("hasAuthority('papel.excluir')")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		roleService.excluir(id);
	}
	
}
