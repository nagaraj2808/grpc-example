package main

import (
  "context"
  "flag"
  "net/http"
  "github.com/golang/glog"
 "google.golang.org/grpc"
   "google.golang.org/grpc/credentials/insecure"
 runtime "github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
  gw "github.com/nagaraj2808/grpc-example/greetings-gateway/go_service/rrgateway/gateway/proto"
)

var (
  // command-line options:
  // gRPC server endpoint
  grpcServerEndpoint = flag.String("endpoint",  "localhost:9090", "gRPC server endpoint")

)

func run() error {
  ctx := context.Background()
  ctx, cancel := context.WithCancel(ctx)
  defer cancel()

  // Register gRPC server endpoint
  // Note: Make sure the gRPC server is running properly and accessible
  mux := runtime.NewServeMux()
  opts := []grpc.DialOption{grpc.WithTransportCredentials(insecure.NewCredentials())}
  err := gw.RegisterGreetingsServiceHandlerFromEndpoint(ctx, mux,  *grpcServerEndpoint, opts)
  if err != nil {
    return err
  }

  // Start HTTP server (and proxy calls to gRPC server endpoint)
  return http.ListenAndServe(":8081", mux)
}

func main() {
  flag.Parse()

  if err := run(); err != nil {
    glog.Fatal(err)
  }
}