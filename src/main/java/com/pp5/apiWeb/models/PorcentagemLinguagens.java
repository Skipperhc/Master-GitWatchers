package com.pp5.apiWeb.models;

import java.text.DecimalFormat;

import com.pp5.apiWeb.helpers.Numbers;

public class PorcentagemLinguagens {

	private String linguagem;
	private Double porcentagem;
	private String usuario;
	private int qtd;
	
	

	public PorcentagemLinguagens(String linguagem, String usuario, int qtd) {
		super();
		this.linguagem = linguagem;
		this.usuario = usuario;
		this.qtd = qtd;
	}

	public PorcentagemLinguagens(String linguagem, int qtd) {
		super();
		this.linguagem = linguagem;
		this.qtd = qtd;
	}

	public PorcentagemLinguagens(String linguagem, Double porcentagem) {
		super();
		this.linguagem = linguagem;
		this.porcentagem = porcentagem;
	}

	public void calcularPercent(int totalQtd) {
		porcentagem = ((double) qtd / (double) totalQtd) * 100;
		      
		porcentagem = Numbers.ArredondarCasasDecimais(porcentagem, 1);
	}

	@Override
	public String toString() {
		return "PercentLanguages [linguagem=" + linguagem + ", porcentagem="
				+ porcentagem + ", qtd=" + qtd + "]";
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((linguagem == null) ? 0 : linguagem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentagemLinguagens other = (PorcentagemLinguagens) obj;
		if (linguagem == null) {
			if (other.linguagem != null)
				return false;
		} else if (!linguagem.equals(other.linguagem))
			return false;
		return true;
	}
}
