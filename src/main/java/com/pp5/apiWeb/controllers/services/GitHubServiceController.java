package com.pp5.apiWeb.controllers.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.pp5.apiWeb.exceptions.CampoInvalidoException;
import com.pp5.apiWeb.helpers.RetornoErroAPI;
import com.pp5.apiWeb.models.ContribuicoesRepositorio;
import com.pp5.apiWeb.models.PorcentagemLinguagens;
import com.pp5.apiWeb.models.Repositorio;
import com.pp5.apiWeb.models.UsuarioContribuicao;

@RestController
@RequestMapping("/github")
public class GitHubServiceController {

	@GetMapping("/{user}/porcentagens")
	public static List<PorcentagemLinguagens> listarPorcentagens(@PathVariable String user) {

		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
				.path("users/" + user + "/repos").build();

		ResponseEntity<Repositorio[]> response = template.getForEntity(uri.toUriString(), Repositorio[].class);
		Repositorio[] listaRepositorioitorios = response.getBody();

		List<Repositorio> repositorios = Arrays.asList(listaRepositorioitorios);

		List<PorcentagemLinguagens> porcentagensLinguagem = new ArrayList<PorcentagemLinguagens>();

		for (Repositorio repos : repositorios) {
			if (repos.getLinguagem() == null) {
				if (!porcentagensLinguagem.stream().anyMatch(x -> x.getLinguagem().equals("Sem linguagem"))) {
					porcentagensLinguagem.add(new PorcentagemLinguagens("Sem linguagem", 1));
				} else {
					PorcentagemLinguagens percent = porcentagensLinguagem.stream()
							.filter(x -> x.getLinguagem().equals("Sem linguagem")).findFirst().orElse(null);
					if (percent != null) {

						int index = porcentagensLinguagem.indexOf(percent);
						percent.setQtd(percent.getQtd() + 1);
						porcentagensLinguagem.set(index, percent);
					} else {
						System.out.println("Erro ao encontrar repos null");
					}
				}

			} else if (!porcentagensLinguagem.stream().anyMatch(x -> x.getLinguagem().equals(repos.getLinguagem()))) {
				porcentagensLinguagem.add(new PorcentagemLinguagens(repos.getLinguagem(), user, 1));
			} else {

				PorcentagemLinguagens percent = porcentagensLinguagem.stream()

						.filter(x -> x.getLinguagem().equals(repos.getLinguagem())).findFirst().orElse(null);
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
//			System.out.println(item.toString() + " qtd total: " + repositorios.size());
		}

		return porcentagensLinguagem;
	}

	@GetMapping("/{user}/{linguagemRepos}/projetos")
	public ResponseEntity listarRepositoriosPorLinguagem(@PathVariable String user,
			@PathVariable String linguagemRepos) {
		try {
			RestTemplate template = new RestTemplate();

			if (linguagemRepos == null || linguagemRepos == "") {
				throw new CampoInvalidoException("Informe a linguagem do reposit�rio.");
			}

			if (user == null || user == "") {
				throw new CampoInvalidoException("Informe o nome do usuario dono do reposit�rio.");
			}

			UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
					.path("users/" + user + "/repos").build();

			ResponseEntity<Repositorio[]> response = template.getForEntity(uri.toUriString(), Repositorio[].class);
			Repositorio[] listaRepositorio = response.getBody();

			if (listaRepositorio == null || listaRepositorio.length == 0)
				throw new CampoInvalidoException("Nenhum repositorio foi encontrado para o usu�rio " + user + ".");

			List<Repositorio> repositorios = Arrays.asList(listaRepositorio);

			List<Repositorio> reposFiltrado = new ArrayList<Repositorio>();

			String linguagemDecodificada = HtmlUtils.htmlEscape(linguagemRepos);

			for (Repositorio repos : repositorios) {

				if (repos.getLinguagem().toUpperCase().equals(linguagemDecodificada.toUpperCase())) {
					reposFiltrado.add(repos);
				}
			}

			if (reposFiltrado == null || reposFiltrado.size() == 0)
				// TODO exception objeto nulo ou vazio
				throw new CampoInvalidoException("Nenhum repositorio foi encontrado para o usuário " + user
						+ " com a linguagem " + linguagemDecodificada + ".");

			return ResponseEntity.status(HttpStatus.OK).body(reposFiltrado);

		} catch (CampoInvalidoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RetornoErroAPI(-1, e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RetornoErroAPI(-4, e.getMessage()));
		}
	}

	// https://api.github.com/repos/nomeUsuario/nomeRepositorio/contributors

	@GetMapping("/{user}/{nomeRepositorio}/contribuicoes")
	public static List<UsuarioContribuicao> listarContribuicoes(@PathVariable String user,
			@PathVariable String nomeRepositorio) {
		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
				.path("repos/" + user + "/" + nomeRepositorio + "/contributors").build();

		ResponseEntity<ContribuicoesRepositorio[]> response = template.getForEntity(uri.toUriString(),
				ContribuicoesRepositorio[].class);
		ContribuicoesRepositorio[] listaContribuicoes = response.getBody();

		List<UsuarioContribuicao> listaMensagem = new ArrayList<UsuarioContribuicao>();

		for (ContribuicoesRepositorio cr : listaContribuicoes) {
			listaMensagem.add(new UsuarioContribuicao(cr.getUser(), cr.getContribuicoes()));
		}

		return listaMensagem;
	}
}
