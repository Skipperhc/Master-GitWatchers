package com.pp5.apiWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LimiteDeChamadaException extends RuntimeException {

	private String mensagem;

	public LimiteDeChamadaException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return "403 = " + mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
