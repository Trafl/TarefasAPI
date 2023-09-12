package com.Pivo.Ivo.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.Pivo.Ivo.domain.model.StatusToDo;
import com.Pivo.Ivo.domain.model.ToDo;
import com.Pivo.Ivo.domain.repository.ToDoRepository;
import com.Pivo.Ivo.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
public class ToDoControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	private ToDoRepository repository;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		 RestAssured.port= port;
		 RestAssured.basePath = "/tarefas";
		 
		 cleaner.clearTables();
		 prepararDados();

	}

	@Test
	void deveRetornarStatus200aoListarTarefas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("$", is(notNullValue()))
            .body("$", isA(List.class)); 
	}
	
	@Test
	void deveRetornarStatus201AoCadastrarEListarTarefas() {
		given()
			.body(corpoTest)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("$", is(notNullValue()))
            .body("$", isA(List.class));;
	}
	
	@Test()
	void deveRetornarStatus400ERespostaCorretaNoDetailSobreOErro() {
		given()
		.body(corpoTestException)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value())
		.body("detail", equalTo("Erro ao validar os campos informados no corpo da resposta."));
		
	}
	
	@Test
	void deveRetornarStatus200EListarAsTarefasAoAtualizarEMostarTarefaAtualizada() {
		given()
			.pathParam("toDoId", 1)
			.body(corpoTest)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.put("/{toDoId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("titulo", hasItem("titulo alterado"))
			.body("descricao", hasItem("descricao alterado"))
			.body("prioridade", hasItem(10))
			.body("$", is(notNullValue()))
            .body("$", isA(List.class));
			
	}
	
	@Test
	void deveRetornarStatus200EListarAposFinalizarATarefaDeId1() {
		given()
			.pathParam("toDoId", 1)
		.when()
			.put("/{toDoId}/finalizar")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("realizada", hasItem("FINALIZADA"))
			.body("$", is(notNullValue()))
            .body("$", isA(List.class));
	
	}
	
	@Test
	void deveRetornarStatus200EListarAposDeletarATarefaDeId1() {
		given()
			.pathParam("toDoId", 1)
		.when()
			.delete("/{toDoId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("$", is(notNullValue()))
            .body("$", isA(List.class));	
	}
	
	
	@Test
	void deveRetornarStatus200ERecebeARepresentaçaoEmPdfDaLista() {
		given()
		.when()
			.get("/pdf")
		.then()
			.statusCode(HttpStatus.OK.value())
			.contentType("application/pdf");
	}
	
	String corpoTest = """
			{
			"titulo":"titulo alterado",
			"descricao": "descricao alterado",
			"prioridade": 10
			}
			
			""";
	
	String corpoTestException = """
			{
			"titulo":"titulo",
			"descricao": "",
			"prioridade": 2
			}
			
			""";
	
	private void prepararDados() {
		ToDo tarefatest1 = new ToDo("Tarefa teste 1", "teste 1", 1, StatusToDo.INICIADA, OffsetDateTime.now());
		repository.save(tarefatest1);
		
		ToDo tarefatest2 = new ToDo("Tarefa teste 2", "teste 2", 1, StatusToDo.INICIADA, OffsetDateTime.now());
		repository.save(tarefatest2);
		
		ToDo tarefatest3 = new ToDo("Tarefa teste 3", "teste 3", 1, StatusToDo.INICIADA, OffsetDateTime.now());
		repository.save(tarefatest3);  
	}
}
