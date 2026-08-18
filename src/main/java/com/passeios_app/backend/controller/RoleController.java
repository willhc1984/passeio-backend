package com.passeios_app.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.model.Role;
import com.passeios_app.backend.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public List<Role> listar(){
		return roleService.listar();
	}
	
	@GetMapping("/{id}")
	public Role buscarPoId(@PathVariable Long id) {
		return roleService.buscarPorId(id);
	}
	
	@PostMapping
	public Role salvar(@RequestBody Role role) {
		return roleService.salvar(role);
	}
	
	@PutMapping("/{id}")
	public Role atualizar(@PathVariable Long id, @RequestBody Role role) {
		return roleService.atualizar(id, role);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		roleService.excluir(id);
	}
	
}
