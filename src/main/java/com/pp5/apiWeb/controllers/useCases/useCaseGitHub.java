package com.pp5.apiWeb.controllers.useCases;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.exceptions.CampoInvalidoException;
import com.pp5.apiWeb.models.ContribuicoesRepositorio;
import com.pp5.apiWeb.models.Repositorio;
import com.pp5.apiWeb.models.UsuarioContribuicao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

public class useCaseGitHub {

    public ModelAndView MontarViewRepositorio(String user, String linguagem){

        ModelAndView model = new ModelAndView("repositorios");

        try 
        {
			if(user == "" || user == null)
            {
                throw new CampoInvalidoException("Informe o usuário.");
			}

            if(linguagem == "" || linguagem == null)
            {
                throw new CampoInvalidoException("Informe a linguagem.");
			}

            ResponseEntity response = new GitHubServiceController().listarRepositoriosPorLinguagem(user, linguagem);

            if(response.getStatusCode() != HttpStatus.OK)
            {
                throw new Exception(response.getBody().toString());
            }

            List<Repositorio> repos = (List<Repositorio>)response.getBody();
			
            model.addObject("repositorios", repos);
		} 
		catch (CampoInvalidoException e) {
			model.addObject("errorMessage", e.getMessage());
		}
		catch (RestClientException e) {
			model.addObject("errorMessage", "Usuário não encontrado");
		} 
		catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}

		return model;
    }

    public ModelAndView MontarViewColaboracoes(String user, String nomeRepositorio){
        ModelAndView model = new ModelAndView("colaboracoes");

        try 
        {
			if(user == "" || user == null)
            {
                throw new CampoInvalidoException("Informe o usuário.");
			}

            if(nomeRepositorio == "" || nomeRepositorio == null)
            {
                throw new CampoInvalidoException("Informe o nome do repositorio.");
			}

            ResponseEntity response = new GitHubServiceController().listarContribuicoes(user, nomeRepositorio);

            if(response.getStatusCode() != HttpStatus.OK)
            {
                throw new Exception(response.getBody().toString());
            }

            List<UsuarioContribuicao> repos = (List<UsuarioContribuicao>)response.getBody();
			
            model.addObject("listaColaboracoes", repos);

            List<List<Object>> chartData = new ArrayList<List<Object>>();

            repos.forEach(item -> {
                chartData.add(List.of(item.getUsuario(), item.getContribuicoes()));
            });

            model.addObject("chartData", chartData);
		} 
		catch (CampoInvalidoException e) {
			model.addObject("errorMessage", e.getMessage());
		}
		catch (RestClientException e) {
			model.addObject("errorMessage", "Usuário não encontrado");
		} 
		catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}

		return model;
    }
    
}
