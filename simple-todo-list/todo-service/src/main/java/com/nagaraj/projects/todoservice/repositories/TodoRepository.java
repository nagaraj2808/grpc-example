package com.nagaraj.projects.todoservice.repositories;

import com.nagaraj.projects.todoservice.domains.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo,String> {
}
