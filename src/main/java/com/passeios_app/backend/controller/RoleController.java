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
import com.passeios_app.backend.model.Role;
import com.passeios_app.backend.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PreAuthorize("hasAuthority('role.visualizar')")
	@GetMapping
	public List<RoleResponseDTO> listar(){
		return roleService.listar();
	}
	
	@PreAuthorize("hasAuthority('role.visualizar')")
	@GetMapping("/{id}")
	public RoleResponseDTO buscarPoId(@PathVariable Long id) {
		return roleService.buscarPorId(id);
	}
	
	@PreAuthorize("hasAuthority('role.criar')")
	@PostMapping
	public RoleResponseDTO salvar(@RequestBody RoleRequestDTO dto) {
		return roleService.salvar(dto);
	}
	
	@PreAuthorize("hasAuthority('role.editar')")
	@PutMapping("/{id}")
	public RoleResponseDTO atualizar(@PathVariable Long id, @RequestBody RoleRequestDTO dto) {
		return roleService.atualizar(id, dto);
	}
	
	@PreAuthorize("hasAuthority('role.excluir')")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		roleService.excluir(id);
	}
	
}
