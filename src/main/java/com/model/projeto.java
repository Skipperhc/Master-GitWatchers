package com.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
public class projeto {
	
	@JsonProperty("name")
	private String nome;

	@JsonProperty("language")
	private String linguagem;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public projeto() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();
		
		try 
		{
				Request request = new Request.Builder()
				  .url("https://api.github.com/users/PedroPort3s/repos")
				  .method("GET", null)
				  .build();
				
				Response response = client.newCall(request).execute();
				
				  String j = response.body().string();
				  
					ObjectMapper objectMapper = new ObjectMapper();
					projeto[] projeto = objectMapper.readValue(j, projeto[].class);
					
					List<projeto> projetos = objectMapper.readValue(j, new TypeReference<List<projeto>>(){});
					
					if(projetos != null) {
						for (projeto p : projetos) {
							System.out.println(p.linguagem + " " + p.nome);	
						}
					}		
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
