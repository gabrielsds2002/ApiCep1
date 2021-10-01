package br.com.brq.api.cep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.brq.api.cep.exceptions.AppException;
import br.com.brq.api.cep.business.RegraDeNegocioBO;
import br.com.brq.api.cep.domain.AcessoRequest;
import br.com.brq.api.cep.domain.AcessoResponse;
import br.com.brq.api.cep.services.CepService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cep")
public class AppController {
	@Autowired
	private CepService cepService;
	
	@Autowired
	private RegraDeNegocioBO regraDeNegocioBO;

	@PostMapping
	public ResponseEntity<AcessoResponse> buscaCep(@RequestBody AcessoRequest request) {
		ResponseEntity<AcessoResponse> response = null;
		try {
			regraDeNegocioBO.validaCep(request);
			AcessoResponse obterResposta = cepService.obterDadosAcessoResponse(request.getCep());
			return new ResponseEntity<AcessoResponse>(obterResposta, HttpStatus.OK);
		} catch (AppException app) {
			String msg = app.getMessage();
			HttpStatus status = HttpStatus.BAD_REQUEST;
			response = obterRespostaErro(msg, status);
			return response;
		} catch (Exception e) {
			return obterRespostaErro("Serviço indisponível", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private ResponseEntity<AcessoResponse> obterRespostaErro(String msg, HttpStatus status) {
		ResponseEntity<AcessoResponse> response;
		AcessoResponse obterResposta = new AcessoResponse();
		obterResposta.setMensagem(msg);
		obterResposta.setCodigo(status.value());
		response = new ResponseEntity<>(obterResposta, status);
		return response;
	}

}
