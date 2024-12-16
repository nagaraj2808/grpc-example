package main

import (
    "context"
    "log"
    "net/http"

    "github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
    "google.golang.org/grpc"
    pb "C:/Users/nagar/OneDrive/Documents/GitHub/grpc-example/greetings-gateway/greetings-common/src/main/proto/greetings.proto"
)

func main() {
    ctx := context.Background()
    ctx, cancel := context.WithCancel(ctx)
    defer cancel()

    mux := runtime.NewServeMux()
    opts := []grpc.DialOption{grpc.WithInsecure()}

    err := pb.RegisterYourServiceHandlerFromEndpoint(ctx, mux, "localhost:50051", opts)
    if err != nil {
        log.Fatalf("Failed to register gRPC-Gateway: %v", err)
    }

    log.Println("Serving gRPC-Gateway on http://localhost:8080")
    http.ListenAndServe(":8080", mux)
}
