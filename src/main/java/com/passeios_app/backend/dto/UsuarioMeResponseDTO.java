package com.passeios_app.backend.dto;

import java.util.List;

public class UsuarioMeResponseDTO {
	
	private Long id;
	private String nome;
	private String email;
	private Long roleId;
	private List<String> permissoes;
	
	public UsuarioMeResponseDTO(Long id, String nome, String email, Long roleId, List<String> permissoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.roleId = roleId;
		this.permissoes = permissoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}
	
	
	
	

}
