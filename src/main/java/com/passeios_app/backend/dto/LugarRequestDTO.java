package com.passeios_app.backend.dto;

public class LugarRequestDTO {
	
	private String nome;
	private String localizacao;
	private String urlfoto;
	private Double avaliacao;
	private Long categoriaId;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getUrlfoto() {
		return urlfoto;
	}
	public void setUrlfoto(String urlfoto) {
		this.urlfoto = urlfoto;
	}
	public Double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

}
