package com.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.model.ContribuicoesRepositorio;
import com.model.PorcentagemLinguagens;
import com.model.Repositorio;

@RestController
@RequestMapping("/github")
public class GitHubController {

	@GetMapping("/{user}")
	public List<PorcentagemLinguagens> listarPorcentagens(
			@PathVariable String user) {
		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host("api.github.com").path("users/" + user + "/repos")
				.build();

		ResponseEntity<Repositorio[]> response = template
				.getForEntity(uri.toUriString(), Repositorio[].class);
		Repositorio[] listaRepositorioitorios = response.getBody();

		List<Repositorio> repositorios = Arrays.asList(listaRepositorioitorios);

		List<PorcentagemLinguagens> porcentagensLinguagem = new ArrayList<PorcentagemLinguagens>();

		for (Repositorio repos : repositorios) {
			if (repos.getLinguagem() == null) {
				if (!porcentagensLinguagem.stream().anyMatch(
						x -> x.getLinguagem().equals("Sem linguagem"))) {
					porcentagensLinguagem
							.add(new PorcentagemLinguagens("Sem linguagem", 1));
				} else {
					PorcentagemLinguagens percent = porcentagensLinguagem
							.stream()
							.filter(x -> x.getLinguagem()
									.equals("Sem linguagem"))
							.findFirst().orElse(null);
					if (percent != null) {
						int index = porcentagensLinguagem.indexOf(percent);
						percent.setQtd(percent.getQtd() + 1);
						porcentagensLinguagem.set(index, percent);
					} else {
						System.out.println("Erro ao encontrar repos null");
					}
				}
			} else if (!porcentagensLinguagem.stream().anyMatch(
					x -> x.getLinguagem().equals(repos.getLinguagem()))) {
				porcentagensLinguagem.add(
						new PorcentagemLinguagens(repos.getLinguagem(), 1));
			} else {
				PorcentagemLinguagens percent = porcentagensLinguagem.stream()
						.filter(x -> x.getLinguagem()
								.equals(repos.getLinguagem()))
						.findFirst().orElse(null);
				if (percent != null) {
					int index = porcentagensLinguagem.indexOf(percent);
					percent.setQtd(percent.getQtd() + 1);
					porcentagensLinguagem.set(index, percent);
				} else {
					System.out.println("Erro ao encontrar repositorio");
				}
			}
		}

		for (PorcentagemLinguagens item : porcentagensLinguagem) {
			item.calcularPercent(repositorios.size());
			System.out.println(
					item.toString() + " qtd total: " + repositorios.size());
		}

		return porcentagensLinguagem;
	}

	// https://api.github.com/repos/nomeUsuario/nomeRepositorio/contributors

	@GetMapping("/{user}/{nomeRepositorio}")
	public List<String> listarContribuicoes(@PathVariable String user,
			@PathVariable String nomeRepositorio) {
		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host("api.github.com")
				.path("repos/" + user + "/" + nomeRepositorio + "/contributors")
				.build();

		ResponseEntity<ContribuicoesRepositorio[]> response = template
				.getForEntity(uri.toUriString(),
						ContribuicoesRepositorio[].class);
		ContribuicoesRepositorio[] listaContribuicoes = response.getBody();

		List<String> listaMensagem = new ArrayList<String>();

		for (ContribuicoesRepositorio cr : listaContribuicoes) {
			listaMensagem.add("Usuario " + cr.getUser()
					+ " contribui para o repositorio " + nomeRepositorio
					+ " com " + cr.getContribuicoes() + " commits.");
		}
		
		return listaMensagem;
	}
}
