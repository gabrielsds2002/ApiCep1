package br.com.brq.api.cep.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoRequest {
	private String cep;

	public String getCep() {
		return cep;
	}



}
