package io.github.rura6502.basic.server;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@Component
public class GrpcServer implements ApplicationRunner {

  public static final int GRPC_SERVER_PORT = 19999;
  public static final Server grpcServer
    = ServerBuilder.forPort(GRPC_SERVER_PORT)
                              .addService(new ExampleServiceImpl())
                              .build();

  @Override
  public void run(ApplicationArguments args) throws Exception {
    grpcServer.start();

  }
  
}
