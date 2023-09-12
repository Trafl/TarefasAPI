package com.Pivo.Ivo.domain.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "to_do")
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private Integer prioridade;
	
	@Enumerated(EnumType.STRING)
	private StatusToDo realizada = StatusToDo.INICIADA;
	
	@CreationTimestamp
	private OffsetDateTime criacao;
	
	private OffsetDateTime finalizacao;

	public ToDo(String titulo, String descricao, Integer prioridade, StatusToDo realizada, OffsetDateTime criacao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.realizada = realizada;
		this.criacao = criacao;
	}
	
	public void endTask() {
		this.realizada = StatusToDo.FINALIZADA;
		this.finalizacao = OffsetDateTime.now(); 
	}

}
