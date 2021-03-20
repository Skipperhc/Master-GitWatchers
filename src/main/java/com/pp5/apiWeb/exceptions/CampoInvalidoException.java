package com.pp5.apiWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampoInvalidoException extends RuntimeException
{	
	private String mensagem;
	
	public CampoInvalidoException()
	{
		
	}

	public CampoInvalidoException(String mensagem)
	{
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString()
	{
		return String.format("400 - %s", mensagem);
	}
}
