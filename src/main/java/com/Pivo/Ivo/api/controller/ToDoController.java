package com.Pivo.Ivo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Pivo.Ivo.api.DTO.ToDoInput;
import com.Pivo.Ivo.api.DTO.ToDoOutput;
import com.Pivo.Ivo.api.controller.openapi.ToDoControllerOpenApi;
import com.Pivo.Ivo.api.mapper.ToDoMapper;
import com.Pivo.Ivo.domain.model.ToDo;
import com.Pivo.Ivo.domain.service.PdfReportService;
import com.Pivo.Ivo.domain.service.ToDoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class ToDoController implements ToDoControllerOpenApi {

	@Autowired
	public ToDoService service;
	
	@Autowired
	public ToDoMapper mapper;
	
	@Autowired
	public PdfReportService pdfReportService; 
	
	@GetMapping
	public ResponseEntity<List<ToDoOutput>> list(){
		List<ToDo> list = service.list();
	
		List<ToDoOutput> listDto = mapper.toCollectionModel(list);
		
		return ResponseEntity.ok(listDto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<ToDoOutput>> create(@RequestBody @Valid ToDoInput input){
		
		ToDo toDo = mapper.toDomainModel(input);
		
		List<ToDo> list = service.create(toDo);
		
		return ResponseEntity.status(201).body(mapper.toCollectionModel(list));
	}
	
	@PutMapping("/{toDoId}")
	public ResponseEntity<List<ToDoOutput>> update(@PathVariable Long toDoId, @RequestBody @Valid ToDoInput input ){
		
		ToDo toDoOrigin = service.serchOne(toDoId);
		
		mapper.copyToDomain(input, toDoOrigin);
		
		service.update(toDoOrigin);
		
		return list();
	}
	
	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> findPdf() {
		
		byte[] bytesPdf = pdfReportService.pdfReport();
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tarefas.pdf");
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).
				headers(headers)
				.body(bytesPdf); 	
	}
	
	@DeleteMapping("/{toDoId}")
	public ResponseEntity<List<ToDoOutput>> delete(@PathVariable Long toDoId){
		service.delete(toDoId);
		return list();
	}

	@PutMapping("{toDoId}/finalizar")
	public ResponseEntity<List<ToDoOutput>> endTask(Long toDoId) {
		service.endTask(toDoId);
		return list();
	}
	
}
