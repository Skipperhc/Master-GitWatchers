package com.pp5.apiWeb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinhasSemana {

	@JsonProperty("w")
	private Long semana;
	@JsonProperty("a")
	private Long adicionadas;
	@JsonProperty("d")
	private Long removidas;
	@JsonProperty("c")
	private Long qtdCommits;

	
	public LinhasSemana() {
		super();
	}
	public LinhasSemana(Long semana, Long adicionadas, Long removidas, Long qtdCommits) {
		super();
		this.semana = semana;
		this.adicionadas = adicionadas;
		this.removidas = removidas;
		this.qtdCommits = qtdCommits;
	}
	@Override
	public String toString() {
		return "LinhasSemana [semana=" + semana + ", adicionadas=" + adicionadas + ", removidas=" + removidas
				+ ", qtdCommits=" + qtdCommits + "]";
	}
	public Long getSemana() {
		return semana;
	}
	public void setSemana(Long semana) {
		this.semana = semana;
	}
	public Long getAdicionadas() {
		return adicionadas;
	}
	public void setAdicionadas(Long adicionadas) {
		this.adicionadas = adicionadas;
	}
	public Long getRemovidas() {
		return removidas;
	}
	public void setRemovidas(Long removidas) {
		this.removidas = removidas;
	}
	public Long getQtdCommits() {
		return qtdCommits;
	}
	public void setQtdCommits(Long qtdCommits) {
		this.qtdCommits = qtdCommits;
	}
}
