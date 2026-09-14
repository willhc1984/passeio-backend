package com.passeios_app.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.dto.PermissaoDTO;
import com.passeios_app.backend.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
	
	private final PermissionService permissionService;
	
	public PermissionController(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@PreAuthorize("hasAuthority('permissao.visualizar')")
	@GetMapping
	public Page<PermissaoDTO> listar(Pageable pageable){
		return permissionService.listar(pageable);
	}
	
	@PreAuthorize("hasAuthority('permissao.visualizar')")
	@GetMapping("/{id}")
	public PermissaoDTO buscarPorId(@PathVariable Long id) {
		return permissionService.buscarPorId(id);
	}
	
	@PreAuthorize("hasAuthority('permissao.excluir')")
	@PostMapping
	public PermissaoDTO salvar(@RequestBody PermissaoDTO dto) {
		return permissionService.salvar(dto);
	}
	
	@PreAuthorize("hasAuthority('permissao.editar')")
	@PutMapping("/{id}")
	public PermissaoDTO atualizar(@PathVariable Long id, @RequestBody PermissaoDTO dto) {
		return permissionService.atualizar(id, dto);
	}
	
	@PreAuthorize("hasAuthority('permissao.excluir')")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		permissionService.excluir(id);
	}

}
