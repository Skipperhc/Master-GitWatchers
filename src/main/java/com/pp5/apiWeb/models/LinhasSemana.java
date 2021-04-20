package com.pp5.apiWeb.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pp5.apiWeb.helpers.Transformador;

public class LinhasSemana {

	private String id;
	private String semana;
	@JsonProperty("a")
	private Long adicionadas;
	@JsonProperty("d")
	private Long removidas;
	@JsonProperty("c")
	private Long qtdCommits;

	@JsonProperty("w")
	private void transformarData(Long semanaUnix) {
		LocalDate inicioSemanaLocalDate = Transformador.transformarUnixLocalDate(semanaUnix);
		LocalDate finalSemanaLocalDate = inicioSemanaLocalDate.plusDays(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		DateTimeFormatter formatterId = DateTimeFormatter.ofPattern("dd_LLLL_yyyy");
		semana = inicioSemanaLocalDate.format(formatter) + " - " + finalSemanaLocalDate.format(formatter);
		id = inicioSemanaLocalDate.format(formatterId) + "_" + finalSemanaLocalDate.format(formatterId);
	}

	public LinhasSemana() {
		super();
	}

	public LinhasSemana(String semana, Long adicionadas, Long removidas, Long qtdCommits) {
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

	public String getSemana() {
		return semana;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSemana(String semana) {
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
