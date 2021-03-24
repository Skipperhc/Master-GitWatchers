package com.pp5.apiWeb.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repositorio {
	private String donoRepos;
	
	@JsonProperty("language")
	private String linguagem;
	
	@JsonProperty("name")
	private String nome;
	
	@JsonProperty("html_url")
	private String url;
	
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
	
	@JsonProperty("owner")
	private void unpackLogin(Map<String, String> owner) {
		donoRepos = owner.get("login");
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
