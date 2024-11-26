package com.nagaraj.projects.greetings_service.services;

import com.nagaraj.projects.grpc.GreetingRequest;
import com.nagaraj.projects.grpc.GreetingResponse;
import com.nagaraj.projects.grpc.GreetingsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreetingsServiceImpl extends GreetingsServiceGrpc.GreetingsServiceImplBase {
    @Override
    public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        String message = request.getGreetingMessage();
        System.out.println(message);
        GreetingResponse response = GreetingResponse.newBuilder()
                .setGreetingMessage("Hello recieved message: " + message)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
