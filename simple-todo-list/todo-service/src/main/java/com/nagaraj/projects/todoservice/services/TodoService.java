package com.nagaraj.projects.todoservice.services;


import com.nagaraj.projects.proto.todoservice.UpdateTodoRequest;
import com.nagaraj.projects.todoservice.domains.Todo;
import com.nagaraj.projects.todoservice.repositories.TodoRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    public List<Todo> listTodos() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(UpdateTodoRequest updateTodoRequest) throws StatusException{
        Todo todo = todoRepository.findByTodoId(updateTodoRequest.getTodoId()).orElse(null);
        if(todo == null){
            throw new StatusException(Status.NOT_FOUND.withDescription("todoId not found"));
        }
        if(!StringUtils.isBlank(updateTodoRequest.getTitle())){
            todo.setTitle(updateTodoRequest.getTitle());
        }
        if(updateTodoRequest.hasDone()){
            todo.setDone(updateTodoRequest.getDone().getValue());
        }
       return todoRepository.save(todo);
    }

    public void deleteTodo(String todoId) throws StatusException{
        Todo todo = todoRepository.findByTodoId(todoId).orElse(null);
        if(todo == null) {
            throw new StatusException(Status.NOT_FOUND.withDescription("Todo not found"));
        }
        todoRepository.deleteById(todoId);
    }
}
