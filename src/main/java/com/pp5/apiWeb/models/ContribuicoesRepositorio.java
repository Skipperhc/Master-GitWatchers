package com.pp5.apiWeb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContribuicoesRepositorio {

	public ContribuicoesRepositorio(String user, int contribuicoes) {
		super();
		this.user = user;
		this.contribuicoes = contribuicoes;
	}

	private ContribuicoesRepositorio() {
	}
	
	@JsonProperty("login")
	private String user;

	@JsonProperty("contributions")
	private int contribuicoes;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getContribuicoes() {
		return contribuicoes;
	}

	public void setContribuicoes(int contribuicoes) {
		this.contribuicoes = contribuicoes;
	}
}
