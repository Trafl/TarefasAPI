package com.Pivo.Ivo.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	

	UNREADABLE_MESSAGE("Mensagem incompreensível."),
	INVALID_DATA("Dados invalidos."),
	TODO_NOTFOUND("Tarefa não registrada");
	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
}
