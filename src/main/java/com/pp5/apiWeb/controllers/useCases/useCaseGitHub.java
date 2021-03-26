package com.pp5.apiWeb.controllers.useCases;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.exceptions.CampoInvalidoException;
import com.pp5.apiWeb.models.Repositorio;

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
    
}
