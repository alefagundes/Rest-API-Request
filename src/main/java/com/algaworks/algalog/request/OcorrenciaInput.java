package com.algaworks.algalog.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class OcorrenciaInput {
	
	@NotBlank
	private String descricao;
}
