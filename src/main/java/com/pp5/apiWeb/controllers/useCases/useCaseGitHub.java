package com.pp5.apiWeb.controllers.useCases;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp5.apiWeb.controllers.services.GitHubServiceController;
import com.pp5.apiWeb.exceptions.CampoInvalidoException;
import com.pp5.apiWeb.exceptions.LimiteDeChamadaException;
import com.pp5.apiWeb.exceptions.NaoEncontradoException;
import com.pp5.apiWeb.models.ContribuicoesRepositorio;
import com.pp5.apiWeb.models.Contribuidor;
import com.pp5.apiWeb.models.ContribuidorSemana;
import com.pp5.apiWeb.models.LinhasSemana;
import com.pp5.apiWeb.models.ListaContribuidoresPorSemana;
import com.pp5.apiWeb.models.ListaPorcentagensRepositorios;
import com.pp5.apiWeb.models.PorcentagemLinguagens;
import com.pp5.apiWeb.models.Repositorio;
import com.pp5.apiWeb.models.UsuarioContribuicao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class useCaseGitHub {

	public ModelAndView MontarViewRepositorio(String user, String linguagem) {

		ModelAndView model = new ModelAndView("repositorios");

		try {
			if (user == "" || user == null) {
				throw new CampoInvalidoException("Informe o usuário.");
			}

			if (linguagem == "" || linguagem == null) {
				throw new CampoInvalidoException("Informe a linguagem.");
			}

			ResponseEntity response = new GitHubServiceController().listarRepositoriosPorLinguagem(user, linguagem);

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new Exception(response.getBody().toString());
			}

			List<Repositorio> repos = (List<Repositorio>) response.getBody();

			model.addObject("repositorios", repos);
		} catch (CampoInvalidoException e) {
			model.addObject("errorMessage", e.getMessage());
		} catch (RestClientException e) {
			model.addObject("errorMessage", "Usuário não encontrado");
		} catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}

		return model;
	}

	public ModelAndView MontarViewColaboracoes(String user, String nomeRepositorio) {
		ModelAndView model = new ModelAndView("colaboracoes");

		try {
			if (user == "" || user == null) {
				throw new CampoInvalidoException("Informe o usuário.");
			}

			if (nomeRepositorio == "" || nomeRepositorio == null) {
				throw new CampoInvalidoException("Informe o nome do repositorio.");
			}

			ResponseEntity response = new GitHubServiceController().listarContribuicoes(user, nomeRepositorio);

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new Exception(response.getBody().toString());
			}

			List<UsuarioContribuicao> repos = (List<UsuarioContribuicao>) response.getBody();

			model.addObject("listaColaboracoes", repos);

			List<List<Object>> chartData = new ArrayList<List<Object>>();

			repos.forEach(item -> {
				chartData.add(List.of(item.getUsuario(), item.getContribuicoes()));
			});

			model.addObject("chartData", chartData);
		} catch (CampoInvalidoException e) {
			model.addObject("errorMessage", e.getMessage());
		} catch (RestClientException e) {
			model.addObject("errorMessage", "Usuário não encontrado");
		} catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}

		return model;
	}

	public ModelAndView viewTeste() {
		ModelAndView model = new ModelAndView("teste");
		return model;
	}

	public ModelAndView montarViewReposDetails(String user, String repos) {
		ModelAndView model = new ModelAndView("detalhesRepositorios");
		model.addObject("usuario", user);
		model.addObject("repos", repos);

		try {

			if (user == null || user == "") {
				throw new CampoInvalidoException("Informe o nome do usuário.");
			}

			ListaPorcentagensRepositorios listaPorcentagens_Repositorios = listarPorcentagens(user);
			model.addObject("listaRepositorios", listaPorcentagens_Repositorios.getListaRepositorios());
			model.addObject("listaPorcentagens", listaPorcentagens_Repositorios.getListaPorcentagens());

			List<List<Object>> chartData = new ArrayList<List<Object>>();

			listaPorcentagens_Repositorios.getListaPorcentagens().forEach(item -> {
				chartData.add(List.of(item.getLinguagem(), item.getQtd()));
			});

			model.addObject("chartData", chartData);

			if (repos != null && repos != "") {
				model.addObject("listaContribuidores", listaContribuidores(user, repos));
			}
		} catch (CampoInvalidoException e) {
			model.addObject("errorMessage", e.getMessage());
		} catch (NaoEncontradoException e) {
			model.addObject("errorMessage", e.getMessage());
		} catch (RestClientException e) {
			model.addObject("errorMessage", e.getMessage());
		} catch (Exception e) {
			model.addObject("errorMessage", e.getMessage());
		}
		return model;
	}

	public ModelAndView montarViewLinhasSemana(String user, String repos) {
		ModelAndView model = new ModelAndView("linhasSemana");
		model.addObject("usuario", user);
		model.addObject("repos", repos);

		try {
			if (user == null || user == "") {
				throw new CampoInvalidoException("Informe o nome do usuário.");
			}

			if (repos == null || repos == "") {
				throw new CampoInvalidoException("Informe o nome do repositório.");
			}

			List<Contribuidor> listaContribuidores = listaContribuidores(user, repos);
			model.addObject("listaContribuidores", listaContribuidores);
			List<ListaContribuidoresPorSemana> semanasFormatadas = formatarSemanas(listaContribuidores);
			model.addObject("listaContribuicoesSemana", semanasFormatadas);
		} catch (CampoInvalidoException | NaoEncontradoException | RestClientException | LimiteDeChamadaException e) {
			model.addObject("errorMessage", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMessage", e.getMessage());
		}
		return model;
	}

	private List<ListaContribuidoresPorSemana> formatarSemanas(List<Contribuidor> listaContribuidores) {
		//um item por semana, com as contribuições de cada um
		List<ListaContribuidoresPorSemana> listaContribuicoesSemana = new ArrayList<ListaContribuidoresPorSemana>();
		//o 1° contribuidor sempre é o dono do repositorio então vo usar ele como base
		Contribuidor contribuidorBase = listaContribuidores.get(0);
		int qtdSemanas = contribuidorBase.getLinhasAlteradas().size();
		int qtdContribuidores = listaContribuidores.size();

		for (int index = 0; index < qtdSemanas; index++) {
			List<ContribuidorSemana> listaContruibuidoresSemana = new ArrayList<ContribuidorSemana>();
			Long linhasAlteradas = 0l;
			Long qtdCommits = 0l;
			for (int indexContribuidor = 0; indexContribuidor < qtdContribuidores; indexContribuidor++) {
				Contribuidor contribuidor = listaContribuidores.get(indexContribuidor);
				linhasAlteradas += contribuidor.getLinhasAlteradas().get(index).getAdicionadas();
				linhasAlteradas += contribuidor.getLinhasAlteradas().get(index).getRemovidas();
				qtdCommits += contribuidor.getLinhasAlteradas().get(index).getQtdCommits();
				listaContruibuidoresSemana
						.add(
								new ContribuidorSemana(
										contribuidor.getNome(), 
										contribuidor.getLinhasAlteradas().get(index).getAdicionadas(),
										contribuidor.getLinhasAlteradas().get(index).getRemovidas(), 
										contribuidor.getLinhasAlteradas().get(index).getQtdCommits()));
			}
			if(linhasAlteradas > 0 || qtdCommits > 0) {
				listaContribuicoesSemana.add(
						new ListaContribuidoresPorSemana(
								contribuidorBase.getLinhasAlteradas().get(index).getId(),
								contribuidorBase.getLinhasAlteradas().get(index).getSemana(),
								linhasAlteradas,
								qtdCommits,
								listaContruibuidoresSemana));
				
			}
		}
		
		listaContribuicoesSemana.forEach(item -> {
			System.out.println(item.getDoDiaAte());
			System.out.println(item.getLinhasAlteradas());
			System.out.println(item.getQtdCommits());
		});
		
		return listaContribuicoesSemana;
	}

	public List<Contribuidor> listaContribuidores(String user, String nomeRepositorio) {

		RestTemplate template = new RestTemplate();

		if (user == "" || user == null) {
			throw new CampoInvalidoException("Informe o usuário.");
		}

		if (nomeRepositorio == "" || nomeRepositorio == null) {
			throw new CampoInvalidoException("Informe o nome do repositorio.");
		}

		List<Contribuidor> listaContribuidores = new ArrayList<Contribuidor>();

		try {

			UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
					.path(String.format("repos/%s/%s/stats/contributors", user, nomeRepositorio)).build();

//			template.getInterceptors().add(
//		    		  new BasicAuthenticationInterceptor("skipperhc", "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"));
			
			ResponseEntity<Contribuidor[]> response = template.getForEntity(uri.toUriString(), Contribuidor[].class);

			if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
				throw new LimiteDeChamadaException("limite de chamadas excedidas. Tente novamente em 1 minuto.");
			}

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new RuntimeException("Usuario não encontrado no github.");
			}

			Contribuidor[] listaRepositorioitorios = response.getBody();

			if (listaRepositorioitorios.length == 0) {
				throw new NaoEncontradoException("Não foi encontrado nenhum Contribuidor.\nverifique os nomes.");
			}

			listaContribuidores = Arrays.asList(listaRepositorioitorios);

			for (Contribuidor contribuidor : listaContribuidores) {
				Long add = 0l;
				Long del = 0l;
				for (LinhasSemana linhas : contribuidor.getLinhasAlteradas()) {
					add += linhas.getAdicionadas();
					del += linhas.getRemovidas();
				}
				contribuidor.setQtdAdicionadas(add);
				contribuidor.setQtdDeletadas(del);
			}
		} catch (RestClientException e) {
			throw e;
		}
		return listaContribuidores;
	}

	public List<Repositorio> listarRepositorios(String user) {
		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
				.path("users/" + user + "/repos").build();

		ResponseEntity<Repositorio[]> response = template.getForEntity(uri.toUriString(), Repositorio[].class);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Usuario não encontrado no github.");
		}

		Repositorio[] listaRepositorioitorios = response.getBody();
		return Arrays.asList(listaRepositorioitorios);
	}

	public ListaPorcentagensRepositorios listarPorcentagens(String user) {

		RestTemplate template = new RestTemplate();

		if (user == "" || user == null) {
			throw new CampoInvalidoException("Informe o usuário.");
		}

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
				.path("users/" + user + "/repos").build();

		ResponseEntity<Repositorio[]> response = template.getForEntity(uri.toUriString(), Repositorio[].class);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Usuario não encontrado no github.");
		}

		Repositorio[] listaRepositorioitorios = response.getBody();

		ListaPorcentagensRepositorios listaPorcentagens_Repositorio = new ListaPorcentagensRepositorios();
		List<Repositorio> repositorios = Arrays.asList(listaRepositorioitorios);
		listaPorcentagens_Repositorio.setListaRepositorios(repositorios);

		for (Repositorio repos : repositorios) {
			if (repos.getLinguagem() == null) {
				if (!listaPorcentagens_Repositorio.getListaPorcentagens().stream()
						.anyMatch(x -> x.getLinguagem().equals("Sem linguagem"))) {
					listaPorcentagens_Repositorio.getListaPorcentagens()
							.add(new PorcentagemLinguagens("Sem linguagem", 1));
				} else {
					PorcentagemLinguagens percent = listaPorcentagens_Repositorio.getListaPorcentagens().stream()
							.filter(x -> x.getLinguagem().equals("Sem linguagem")).findFirst().orElse(null);
					if (percent != null) {

						int index = listaPorcentagens_Repositorio.getListaPorcentagens().indexOf(percent);
						percent.setQtd(percent.getQtd() + 1);
						listaPorcentagens_Repositorio.getListaPorcentagens().set(index, percent);
					} else {
						System.out.println("Erro ao encontrar repos null");
					}
				}

			} else if (!listaPorcentagens_Repositorio.getListaPorcentagens().stream()
					.anyMatch(x -> x.getLinguagem().equals(repos.getLinguagem()))) {
				listaPorcentagens_Repositorio.getListaPorcentagens()
						.add(new PorcentagemLinguagens(repos.getLinguagem(), user, 1));
			} else {

				PorcentagemLinguagens percent = listaPorcentagens_Repositorio.getListaPorcentagens().stream()

						.filter(x -> x.getLinguagem().equals(repos.getLinguagem())).findFirst().orElse(null);
				if (percent != null) {

					int index = listaPorcentagens_Repositorio.getListaPorcentagens().indexOf(percent);
					percent.setQtd(percent.getQtd() + 1);
					listaPorcentagens_Repositorio.getListaPorcentagens().set(index, percent);
				} else {
					System.out.println("Erro ao encontrar repositorio");
				}
			}
		}

		for (PorcentagemLinguagens item : listaPorcentagens_Repositorio.getListaPorcentagens()) {
			item.calcularPercent(repositorios.size());
			// System.out.println(item.toString() + " qtd total: " + repositorios.size());
		}

		return listaPorcentagens_Repositorio;
	}

}
