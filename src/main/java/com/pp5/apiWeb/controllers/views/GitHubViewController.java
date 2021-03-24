package com.pp5.apiWeb.controllers.views;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.models.PorcentagemLinguagens;
import com.pp5.apiWeb.models.UsuarioContribuicao;

@Controller
public class GitHubViewController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showUserList() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}

	@RequestMapping(value = "/colaboracoes", method = RequestMethod.GET)
	public ModelAndView showColabs(String user, String nomeRepositorio) {
		ModelAndView model = new ModelAndView("colaboracoes");
		return model;
	}

}
