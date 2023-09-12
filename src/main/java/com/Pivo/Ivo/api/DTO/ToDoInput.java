package com.Pivo.Ivo.api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoInput {

	@Schema(example = "Limpar a casa")
	@NotBlank
	private  String titulo;
	
	@Schema(example = "Limpar a casa minunciosamente com cuidado redobrado com os cantos")
	@NotBlank
	private String descricao;
	
	@Schema(example = "1")
	@NotNull
	private Integer prioridade;
	

}
