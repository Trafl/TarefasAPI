package com.Pivo.Ivo.domain.service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pivo.Ivo.api.DTO.ToDoPdf;
import com.Pivo.Ivo.domain.exception.ToDoException;
import com.Pivo.Ivo.domain.model.ToDo;
import com.Pivo.Ivo.domain.repository.ToDoRepository;

import jakarta.transaction.Transactional;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository repository;
	
	public ToDo serchOne (Long toDoId) {
		
		ToDo toDo = repository.findById(toDoId)
				.orElseThrow(()-> new ToDoException(String.format("Tarefa de Id %s não esta registrada no sistema", toDoId)));
		
		return toDo;
	}
	
	public List<ToDo> list(){
		
		List<ToDo> list = repository.findAll();
		
		list.sort(Comparator.comparingInt(ToDo::getPrioridade));
		
		return list;
	}
	
	@Transactional
	public List<ToDo> create(ToDo toDo){
		repository.save(toDo);
		return list();
	}
	
	@Transactional
	public void update(ToDo toDo) {
		
		repository.save(toDo);
		
	}
	
	@Transactional
	public void delete(Long toDoId){
		
		var toDo = serchOne(toDoId);
		
		if(toDo != null) {
			repository.deleteById(toDoId);
		}
		
	}
	
	@Transactional
	public void endTask(Long todoId) {
		var toDo = serchOne(todoId);
		toDo.endTask();
	}
	
	public List<ToDoPdf> findRentForPdf() {
		var toDoList = list();
		
		return toDoList.stream().map((toDo) -> 
					ToDoPdf.builder()
					.titulo(toDo.getTitulo())
					.descricao(toDo.getDescricao())
					.criacao(convertOffSetForDate(toDo.getCriacao()))
					.realizada(toDo.getRealizada().toString())
					.build()
				).collect(Collectors.toList());	 	
	}
	
	public Date convertOffSetForDate(OffsetDateTime time) {
		return Date.from(time.toInstant());
	}
	
}
