package com.nagaraj.projects.todoservice.services;


import com.nagaraj.projects.todoservice.domains.Todo;
import com.nagaraj.projects.todoservice.repositories.TodoRepository;
import io.grpc.Status;
import io.grpc.StatusException;
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

    public Todo getTodo(String todoId) throws StatusException {
        Todo todo = todoRepository.findByTodoId(todoId).orElse(null);
        if(todo == null){
            throw new StatusException(Status.NOT_FOUND.withDescription("Todo with " +  todoId + " not found"));
        }
        return todo;
    }
}
