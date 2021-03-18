package com.helpers;

public class RetornoErroAPI
{
	private int codigo;
	private String mensagem;
	
	public RetornoErroAPI()
	{

	}

	public RetornoErroAPI(int codigo, String mensagem)
	{
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}
}
