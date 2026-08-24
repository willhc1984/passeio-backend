package com.passeios_app.backend.dto;

import java.util.List;

public class RoleRequestDTO {
	
	private Long Id;
	private String nome;
	private String descricao;
	private List<Long> permissoesIds;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Long> getPermissoesIds() {
		return permissoesIds;
	}
	public void setPermissoesIds(List<Long> permissoesIds) {
		this.permissoesIds = permissoesIds;
	}

}
