package com.pp5.apiWeb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contribuidor {

	private String nome;
	private String perfilLink;
	private String imgPerfil;
	@JsonProperty("total")
	private int contribuicoes;
	@JsonProperty("weeks")
	private List<LinhasSemana> linhasAlteradas = new ArrayList<LinhasSemana>();

	public Contribuidor() {
		super();
	}
	public Contribuidor(String nome, String perfilLink, String imgPerfil, int contribuicoes) {
		super();
		this.nome = nome;
		this.perfilLink = perfilLink;
		this.imgPerfil = imgPerfil;
		this.contribuicoes = contribuicoes;
	}
	
	@JsonProperty("author")
	private void unpackLogin(Map<String, String> owner) {
		nome = owner.get("login");
		perfilLink = owner.get("html_url");
		imgPerfil = owner.get("avatar_url");
	}
	
	@Override
	public String toString() {
		return "Contribuidor [nome=" + nome + ", perfilLink=" + perfilLink + ", imgPerfil=" + imgPerfil
				+ ", contribuicoes=" + contribuicoes + "]";
	}
	public List<LinhasSemana> getLinhasAlteradas() {
		return linhasAlteradas;
	}
	public void setLinhasAlteradas(List<LinhasSemana> linhasAlteradas) {
		this.linhasAlteradas = linhasAlteradas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPerfilLink() {
		return perfilLink;
	}
	public void setPerfilLink(String perfilLink) {
		this.perfilLink = perfilLink;
	}
	public String getImgPerfil() {
		return imgPerfil;
	}
	public void setImgPerfil(String imgPerfil) {
		this.imgPerfil = imgPerfil;
	}
	public int getContribuicoes() {
		return contribuicoes;
	}
	public void setContribuicoes(int contribuicoes) {
		this.contribuicoes = contribuicoes;
	}
}
