package com.nagaraj.projects.todoservice.resources;

import com.nagaraj.projects.proto.todoservice.*;
import com.nagaraj.projects.todoservice.converters.TodoConverter;
import com.nagaraj.projects.todoservice.services.TodoService;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@GrpcService
@Slf4j
public class TodoListServiceImpl extends ToDoListServiceGrpc.ToDoListServiceImplBase {
    @Autowired
    private TodoService todoService;

    public TodoListServiceImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void createTodo(CreateTodoRequest request, StreamObserver<Todo> responseObserver) {
        try {
            String title = request.getTitle();
            if (StringUtils.isBlank(title)) {
                throw new StatusException(Status.INVALID_ARGUMENT.withDescription("Title should not be empty"));
            }
            Todo createdTodo = TodoConverter.fromEntityToProto(todoService.createTodo(title));
            responseObserver.onNext(createdTodo);
            responseObserver.onCompleted();
        } catch (StatusException statusException){
            log.error("todo title is empty");
            responseObserver.onError(statusException);
        }
    }

    @Override
    public void getTodo(GetTodoRequest request, StreamObserver<Todo> responseObserver) {
        try{
            String todoId = request.getTodoId().trim();
            if(StringUtils.isBlank(todoId)){
                throw new StatusException(Status.INVALID_ARGUMENT.withDescription("todoId should not be empty"));
            }
            Todo todo = TodoConverter.fromEntityToProto(todoService.getTodo(todoId));
            responseObserver.onNext(todo);
            responseObserver.onCompleted();
        } catch (StatusException statusException){
            log.error(statusException.getMessage());
            responseObserver.onError(statusException);
        }
    }

    @Override
    public void listTodo(ListTodoRequest request, StreamObserver<ListTodoResponse> responseObserver) {
        List<com.nagaraj.projects.todoservice.domains.Todo> todos = todoService.listTodos();
        var response = ListTodoResponse.newBuilder()
                        .addAllTodos(todos.stream().map(TodoConverter::fromEntityToProto).toList())
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
