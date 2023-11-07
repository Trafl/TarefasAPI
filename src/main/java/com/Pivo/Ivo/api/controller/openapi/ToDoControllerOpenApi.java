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

@Tag(name = "Tasks", description = "Task Manager")
public interface ToDoControllerOpenApi {

	 @Operation(summary = "List the tasks", description = "View all available tasks in order of priority",
			 responses = @ApiResponse(responseCode = "200"))
	public ResponseEntity<List<ToDoOutput>> list();
	
	 @Operation(summary = "Register a task", description = "Registers a task in the database.")
	public ResponseEntity<List<ToDoOutput>> create(@RequestBody @Valid ToDoInput input);
	
	 @Operation(summary = "Update a task", description = "Updates a task in the database.",
			 responses = {@ApiResponse(responseCode = "200"),
					 	  @ApiResponse(responseCode = "404", description = "Task not found",
					 	  content = @Content(schema = @Schema(ref = "Problema")))
			})
	public ResponseEntity<List<ToDoOutput>> update(@PathVariable Long toDoId, @RequestBody @Valid ToDoInput input );
	
	 @Operation(summary = "Deletes a task", description = "Deletes a task from the database.",
			 responses = {@ApiResponse(responseCode = "200"),
					 	  @ApiResponse(responseCode = "404", description = "Task not found",
					 	  content = @Content(schema = @Schema(ref = "Problema")))
			})
	public ResponseEntity<List<ToDoOutput>> delete(@PathVariable Long toDoId);
	
	 @Operation(summary = "Generate task PDF", description = "Generates a PDF with all database tasks",
			 responses = @ApiResponse(responseCode = "200"))
	public ResponseEntity<byte[]> findPdf();
	 
	 @Operation(summary = "Finish the task", description = "Finish the selected task",
			 responses = {@ApiResponse(responseCode = "200"),
					 @ApiResponse(responseCode = "404", description = "Task not found",
				 	  content = @Content(schema = @Schema(ref = "Problema")))})
	public ResponseEntity<List<ToDoOutput>> endTask(@PathVariable Long toDoId);
}
