package com.pp5.apiWeb.controllers.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.models.ListaPorcentagensRepositorios;

@Controller
public class GitHubViewController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showUserList(String user,
			@Nullable String nomeRepositorio) {

		ModelAndView model = new ModelAndView("index");

		try {
			if (user != null) {

				ListaPorcentagensRepositorios listaPorcentagens_Repositorios = GitHubServiceController
						.listarPorcentagens(user);
				model.addObject("listaPorcentagens",
						listaPorcentagens_Repositorios.getListaPorcentagens());
				model.addObject("listaRepositorios",
						listaPorcentagens_Repositorios.getListaRepositorios());

				List<List<Object>> chartData = new ArrayList<List<Object>>();

				listaPorcentagens_Repositorios.getListaPorcentagens()
						.forEach(item -> {
							chartData.add(List.of(item.getLinguagem(),
									item.getQtd()));
						});

				model.addObject("chartData", chartData);
			} else {
				throw new Exception(
						"Erro ao obter o lista de porcentagens, tente novamente");
			}
		} catch (RestClientException e) {
			model.addObject("errorMessage", "Usuário não encontrado");
		} catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}

		return model;
	}

	@RequestMapping(value = "/colaboracoes", method = RequestMethod.GET)
	public ModelAndView showColabs(String user, String nomeRepositorio) {
		ModelAndView model = new ModelAndView("colaboracoes");
		return model;
	}

}
