package com.passeios_app.backend.dto;

public class LoginErrorDTO {
	
	private String mensagem;
	
	public LoginErrorDTO(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
