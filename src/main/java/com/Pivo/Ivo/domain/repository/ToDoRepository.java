package com.Pivo.Ivo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pivo.Ivo.domain.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
