package com.pp5.apiWeb.controllers.views;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.models.PorcentagemLinguagens;
import com.pp5.apiWeb.models.Repositorio;

@Controller
public class GitHubViewController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showUserList(String user) {
		ModelAndView model = new ModelAndView("index");
		if(user != null) {
			System.out.println("Ele chegou aqui e deu o seguinte item " + user);
			
			List<PorcentagemLinguagens> lista = GitHubServiceController.listarPorcentagens(user);
			model.addObject("listaPorcentagens", lista);
		} else System.out.println("deu ruim");
		
		
	    return model;
	}

}
