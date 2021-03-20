package com.pp5.apiWeb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repositorio {

	@JsonProperty("language")
	private String linguagem;
	
	@JsonProperty("name")
	private String nome;

	
	public Repositorio() {
		super();
	}

	public Repositorio(String linguagem, String nome) {
		super();
		this.linguagem = linguagem;
		this.nome = nome;
	}

	public String getLinguagem() {
		return linguagem;
	}
	
	public String getNome() {
		return nome;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	@Override
	public String toString() {
		return "Repositorio [linguagem=" + linguagem + " nome="+ nome + "]";
	}
}
