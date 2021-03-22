package com.pp5.apiWeb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repositorio {

	private String donoRepos;
	
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

	public Repositorio(String donoRepos, String linguagem, String nome) {
		super();
		this.donoRepos = donoRepos;
		this.linguagem = linguagem;
		this.nome = nome;
	}
	
	public String getDonoRepos() {
		return donoRepos;
	}

	public void setDonoRepos(String donoRepos) {
		this.donoRepos = donoRepos;
	}

	public void setNome(String nome) {
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
