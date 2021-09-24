package br.com.brq.api.cep.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.brq.api.cep.exceptions.AppException;
import br.com.brq.api.cep.domain.AcessoRequest;
import br.com.brq.api.cep.domain.AcessoResponse;

@Service
public class CepService {
	private String urlCep = "https://viacep.com.br/ws/";
	private String opcoes = "/json";

	public AcessoResponse obterAcessoResponse(AcessoRequest request) {
		return new AcessoResponse();
	}

	public AcessoResponse obterDadosAcessoResponse(String acesso) throws InterruptedException, ExecutionException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(urlCep + acesso + opcoes)).build();
		CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());
		response.get().statusCode();
		if (response.get().statusCode() == 200) {
			String rawResult = response.get().body().toString();
			return new Gson().fromJson(rawResult, AcessoResponse.class);
		} else {
			throw new AppException("Endereço não encontrado!");
		}

	}

}
