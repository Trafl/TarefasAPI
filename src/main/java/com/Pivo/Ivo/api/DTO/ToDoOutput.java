package com.Pivo.Ivo.api.DTO;

import java.time.OffsetDateTime;

import com.Pivo.Ivo.domain.model.StatusToDo;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoOutput {

	@Schema(example = "Limpar a casa")
	private  String titulo;
	
	@Schema(example = "Limpar a casa minunciosamente com cuidado redobrado com os cantos")
	private String descricao;
	
	@Schema(example = "1")
	private Integer prioridade;
	
	@Schema(example = "INICIADA")
	private StatusToDo realizada;

	@Schema(example = "2023-09-07T18:55:08.9696845-03:00")
	private OffsetDateTime criacao;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(example = "2023-09-07T20:45:06.9696845-03:00")
	private OffsetDateTime finalizacao;
}
