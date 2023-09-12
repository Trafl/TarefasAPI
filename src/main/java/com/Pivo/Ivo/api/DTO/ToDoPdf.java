package com.Pivo.Ivo.api.DTO;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ToDoPdf {

	private String titulo;
	
	private String descricao;
	
	private String realizada;

	private Date criacao;
	
}
