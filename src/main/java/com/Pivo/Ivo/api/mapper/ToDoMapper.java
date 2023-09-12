package com.Pivo.Ivo.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Pivo.Ivo.api.DTO.ToDoInput;
import com.Pivo.Ivo.api.DTO.ToDoOutput;
import com.Pivo.Ivo.domain.model.ToDo;

@Component
public class ToDoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
		
		
	public ToDo toDomainModel(ToDoInput ToDoInput) {
		return modelMapper.map(ToDoInput, ToDo.class);
	}
	
	public List<ToDo> toDomainCollectionModel(List<ToDoInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//---------------------------------------------------------

	
	public ToDoOutput toModel(ToDo toDo) {
		return modelMapper.map(toDo, ToDoOutput.class);
	}
	

	public List<ToDoOutput> toCollectionModel(List<ToDo> list) {
		return list.stream().map((model) -> toModel(model)).collect(Collectors.toList());
				
	}

	//----------------------------------------------------------
	public void copyToDomain(ToDoInput ToDoInput, ToDo ToDo) {	 
		modelMapper.map(ToDoInput,ToDo);
	}

}
	
