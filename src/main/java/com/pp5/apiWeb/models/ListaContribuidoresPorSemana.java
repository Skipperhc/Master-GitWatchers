package com.pp5.apiWeb.models;

import java.util.ArrayList;
import java.util.List;

public class ListaContribuidoresPorSemana {

	private String id;
	private String doDiaAte;
	private Long linhasAlteradas;
	private Long qtdCommits;
	private List<ContribuidorSemana> contribuidoresDaSemana = new ArrayList<ContribuidorSemana>();

	public ListaContribuidoresPorSemana() {
		super();
	}

	public ListaContribuidoresPorSemana(String id, String doDiaAte, Long linahsAlteradas, Long qtdCommits,
			List<ContribuidorSemana> contribuidoresDaSemana) {
		super();
		this.id = id;
		this.doDiaAte = doDiaAte;
		this.linhasAlteradas = linahsAlteradas;
		this.qtdCommits = qtdCommits;
		this.contribuidoresDaSemana = contribuidoresDaSemana;
	}

	public Long getLinhasAlteradas() {
		return linhasAlteradas;
	}

	public void setLinhasAlteradas(Long linhasAlteradas) {
		this.linhasAlteradas = linhasAlteradas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getQtdCommits() {
		return qtdCommits;
	}

	public void setQtdCommits(Long qtdCommits) {
		this.qtdCommits = qtdCommits;
	}

	public String getDoDiaAte() {
		return doDiaAte;
	}

	public void setDoDiaAte(String doDiaAte) {
		this.doDiaAte = doDiaAte;
	}

	public List<ContribuidorSemana> getContribuidoresDaSemana() {
		return contribuidoresDaSemana;
	}

	public void setContribuidoresDaSemana(List<ContribuidorSemana> contribuidoresDaSemana) {
		this.contribuidoresDaSemana = contribuidoresDaSemana;
	}
}
