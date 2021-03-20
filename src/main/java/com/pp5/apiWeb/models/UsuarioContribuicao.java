package com.pp5.apiWeb.models;

public class UsuarioContribuicao {

	private String usuario;
	
	private int contribuicoes;

	public UsuarioContribuicao() {
		super();
	}

	public UsuarioContribuicao(String usuario, int contribuicoes) {
		super();
		this.usuario = usuario;
		this.contribuicoes = contribuicoes;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getContribuicoes() {
		return contribuicoes;
	}

	public void setContribuicoes(int contribuicoes) {
		this.contribuicoes = contribuicoes;
	}
	
}
