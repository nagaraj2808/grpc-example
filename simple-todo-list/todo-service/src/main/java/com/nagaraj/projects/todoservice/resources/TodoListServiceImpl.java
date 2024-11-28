package com.nagaraj.projects.todoservice.resources;

import com.nagaraj.projects.proto.todoservice.CreateTodoRequest;
import com.nagaraj.projects.proto.todoservice.ToDoListServiceGrpc;
import com.nagaraj.projects.proto.todoservice.Todo;
import com.nagaraj.projects.todoservice.services.TodoService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class TodoListServiceImpl extends ToDoListServiceGrpc.ToDoListServiceImplBase {
    @Autowired
    private TodoService todoService;
    @Override
    public void createTodo(CreateTodoRequest request, StreamObserver<Todo> responseObserver) {

    }
}
