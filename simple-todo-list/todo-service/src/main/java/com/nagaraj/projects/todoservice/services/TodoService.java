package com.nagaraj.projects.todoservice.services;


import com.nagaraj.projects.todoservice.domains.Todo;
import com.nagaraj.projects.todoservice.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(String title){
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDone(false);
        return todoRepository.save(todo);
    }
}
