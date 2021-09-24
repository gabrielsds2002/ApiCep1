package br.com.brq.api.cep.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String cep;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String 	logradouro;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String localidade;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String complemento;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String bairro;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mensagem;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int codigo;

}
