package com.pp5.apiWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {
	private String mensagem;

	public NaoEncontradoException() {

	}

	public NaoEncontradoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return String.format("404 - %s", mensagem);
	}
}
