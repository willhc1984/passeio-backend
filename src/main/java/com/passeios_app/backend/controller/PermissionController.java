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

import com.passeios_app.backend.model.Permissao;
import com.passeios_app.backend.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
	
	private final PermissionService permissionService;
	
	public PermissionController(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@GetMapping
	public List<Permissao> listar(){
		return permissionService.listar();
	}
		
	@GetMapping("/{id}")
	public Permissao buscarPorId(@PathVariable Long id) {
		return permissionService.buscarPorId(id);
	}
	
	@PostMapping
	public Permissao salvar(@RequestBody Permissao permissao) {
		return permissionService.salvar(permissao);
	}
	
	@PutMapping("/{id}")
	public Permissao atualizar(@PathVariable Long id, @RequestBody Permissao permissao) {
		return permissionService.atualizar(id, permissao);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		permissionService.excluir(id);
	}

}
