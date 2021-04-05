package com.pp5.apiWeb.controllers.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.controllers.useCases.useCaseGitHub;
import com.pp5.apiWeb.exceptions.CampoInvalidoException;
import com.pp5.apiWeb.models.ListaPorcentagensRepositorios;

@Controller
public class GitHubViewController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showUserList(String user) {

		ModelAndView model = new ModelAndView("index");

		try {
			if (user != null) {

				ListaPorcentagensRepositorios listaPorcentagens_Repositorios = GitHubServiceController
						.listarPorcentagens(user);
				model.addObject("listaPorcentagens", listaPorcentagens_Repositorios.getListaPorcentagens());
				model.addObject("listaRepositorios", listaPorcentagens_Repositorios.getListaRepositorios());

				List<List<Object>> chartData = new ArrayList<List<Object>>();

				listaPorcentagens_Repositorios.getListaPorcentagens().forEach(item -> {
					chartData.add(List.of(item.getLinguagem(), item.getQtd()));
				});

				model.addObject("chartData", chartData);
			} else {
				throw new Exception("Erro ao obter o lista de porcentagens, tente novamente");
			}
		} catch (RestClientException e) {
			model.addObject("errorMessage", "Usuário não encontrado");
		} catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}

		return model;
	}

	@RequestMapping(value = "/colaboracoes", method = RequestMethod.GET)
    public ModelAndView showColabsHTML(String user, String nomeRepositorio) {
        ModelAndView model = new ModelAndView("colaboracoes");
        return model;
    }

	@RequestMapping(value = "/colaboracoesPorUsuario", method = RequestMethod.GET)
	public ModelAndView showColabs(
		@RequestParam(value = "user", required = false) String user,
		@RequestParam(value = "nomeRepositorio", required = false) String nomeRepositorio)  
	{
		useCaseGitHub useCaseGithub = new useCaseGitHub();

		return useCaseGithub.MontarViewColaboracoes(user, nomeRepositorio);
	}

	@RequestMapping(value = "/repositorios", method = RequestMethod.GET)
	public ModelAndView showReposHTML()  
	{
		ModelAndView model = new ModelAndView("repositorios");
        return model;
	}

	@RequestMapping(value = "/repositoriosPorLinguagem", method = RequestMethod.GET)
	public ModelAndView showRepos(
		@RequestParam(value = "user", required = false) String user,
		@RequestParam(value = "linguagem", required = false) String linguagem)  
	{
		useCaseGitHub useCaseGithub = new useCaseGitHub();

		return useCaseGithub.MontarViewRepositorio(user, linguagem);
	}
	
	@RequestMapping(value = "/detalhesrepositorios", method = RequestMethod.GET)
	public ModelAndView showReposDetails(
		@RequestParam(value = "user", required = false) String user,
		@RequestParam(value = "repositorio", required = false) String repositorio)  
	{
		useCaseGitHub useCaseGithub = new useCaseGitHub();

		return useCaseGithub.montarViewReposDetails(user, repositorio);
	}
	
	@RequestMapping(value = "/detalhes", method = RequestMethod.GET)
	public ModelAndView showReposDetails()  
	{
		useCaseGitHub useCaseGithub = new useCaseGitHub();

		return useCaseGithub.viewDetalhes();
	}
}
