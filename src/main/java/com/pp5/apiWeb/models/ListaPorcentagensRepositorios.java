package com.pp5.apiWeb.models;

import java.util.ArrayList;
import java.util.List;

public class ListaPorcentagensRepositorios {

	private List<PorcentagemLinguagens> listaPorcentagens;
	private List<Repositorio> listaRepositorios;

	public ListaPorcentagensRepositorios() {
		super();
		listaPorcentagens = new ArrayList<PorcentagemLinguagens>();
		listaRepositorios = new ArrayList<Repositorio>();
	}

	public ListaPorcentagensRepositorios(List<PorcentagemLinguagens> listaPorcentagens, List<Repositorio> listaRepositorios) {
		super();
		this.listaPorcentagens = listaPorcentagens;
		this.listaRepositorios = listaRepositorios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaPorcentagens == null) ? 0 : listaPorcentagens.hashCode());
		result = prime * result + ((listaRepositorios == null) ? 0 : listaRepositorios.hashCode());
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
		ListaPorcentagensRepositorios other = (ListaPorcentagensRepositorios) obj;
		if (listaPorcentagens == null) {
			if (other.listaPorcentagens != null)
				return false;
		} else if (!listaPorcentagens.equals(other.listaPorcentagens))
			return false;
		if (listaRepositorios == null) {
			if (other.listaRepositorios != null)
				return false;
		} else if (!listaRepositorios.equals(other.listaRepositorios))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModelIndex [listaPorcentagens=" + listaPorcentagens + ", listaRepositorios=" + listaRepositorios + "]";
	}

	public List<PorcentagemLinguagens> getListaPorcentagens() {
		return listaPorcentagens;
	}

	public void setListaPorcentagens(List<PorcentagemLinguagens> listaPorcentagens) {
		this.listaPorcentagens = listaPorcentagens;
	}

	public List<Repositorio> getListaRepositorios() {
		return listaRepositorios;
	}

	public void setListaRepositorios(List<Repositorio> listaRepositorios) {
		this.listaRepositorios = listaRepositorios;
	}
}
