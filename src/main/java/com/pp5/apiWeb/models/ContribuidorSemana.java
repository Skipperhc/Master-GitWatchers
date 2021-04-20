package com.pp5.apiWeb.models;

public class ContribuidorSemana {

	private String nome;
	private Long linhasAdicionadas;
	private Long linhasDeletadas;
	private Long qtdCommits;

	public ContribuidorSemana() {
		super();
	}

	public ContribuidorSemana(String nome, Long linhasAdicionadas, Long linhasdeletadas, Long qtdCommits) {
		super();
		this.nome = nome;
		this.linhasAdicionadas = linhasAdicionadas;
		this.linhasDeletadas = linhasdeletadas;
		this.qtdCommits = qtdCommits;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getLinhasAdicionadas() {
		return linhasAdicionadas;
	}

	public void setLinhasAdicionadas(Long linhasAdicionadas) {
		this.linhasAdicionadas = linhasAdicionadas;
	}

	public Long getLinhasDeletadas() {
		return linhasDeletadas;
	}

	public void setLinhasDeletadas(Long linhasdeletadas) {
		this.linhasDeletadas = linhasdeletadas;
	}

	public Long getQtdCommits() {
		return qtdCommits;
	}

	public void setQtdCommits(Long qtdCommits) {
		this.qtdCommits = qtdCommits;
	}
}
