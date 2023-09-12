package com.Pivo.Ivo.api.controller.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.Pivo.Ivo.api.DTO.ToDoInput;
import com.Pivo.Ivo.api.DTO.ToDoOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Tarefas", description = "Gerenciador de tarefas")
public interface ToDoControllerOpenApi {

	 @Operation(summary = "Listar as tarefas", description = "Vizualiza todas as tarefas disponiveis por ordem de prioridade",
			 responses = @ApiResponse(responseCode = "200"))
	public ResponseEntity<List<ToDoOutput>> list();
	
	 @Operation(summary = "Registra uma tarefa", description = "Registra uma tarefa no banco de dados.")
	public ResponseEntity<List<ToDoOutput>> create(@RequestBody @Valid ToDoInput input);
	
	 @Operation(summary = "Atualiza uma tarefa", description = "Atualiza uma tarefa no banco de dados.",
			 responses = {@ApiResponse(responseCode = "200"),
					 	  @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
					 	  content = @Content(schema = @Schema(ref = "Problema")))
			})
	public ResponseEntity<List<ToDoOutput>> update(@PathVariable Long toDoId, @RequestBody @Valid ToDoInput input );
	
	 @Operation(summary = "Deleta uma tarefa", description = "Deleta uma tarefa no banco de dados.",
			 responses = {@ApiResponse(responseCode = "204",
				 	  content = @Content(schema = @Schema(ref = ""))),
					 	  @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
					 	  content = @Content(schema = @Schema(ref = "Problema")))
			})
	public ResponseEntity<List<ToDoOutput>> delete(@PathVariable Long toDoId);
	
	 @Operation(summary = "Gerar PDF de tarefas", description = "Gera um pdf com todas as tarefas do banco de dados",
			 responses = @ApiResponse(responseCode = "200"))
	public ResponseEntity<byte[]> findPdf();
	 
	 @Operation(summary = "Finalizar a tarefa", description = "Finaliza a tarefas selecionada",
			 responses = {@ApiResponse(responseCode = "200"),
					 @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
				 	  content = @Content(schema = @Schema(ref = "Problema")))})
	public ResponseEntity<List<ToDoOutput>> endTask(@PathVariable Long toDoId);
}
