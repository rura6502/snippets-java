package io.github.rura6502.basic.client;

import org.springframework.stereotype.Service;

import io.github.rura6502.basic.ExampleProtoRequest;
import io.github.rura6502.basic.ExampleProtoResponse;
import io.github.rura6502.basic.ExampleServiceGrpc;
import io.github.rura6502.basic.server.GrpcServer;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrpcClient {

  public static final String GRPC_HOST = "localhost";
  public final ExampleServiceGrpc.ExampleServiceStub asyncStub
    = ExampleServiceGrpc.newStub(
      ManagedChannelBuilder.forAddress(GRPC_HOST, GrpcServer.GRPC_SERVER_PORT)
        .usePlaintext()
        .build()
    );

  public void exampleCall() {
    final ExampleProtoRequest req = ExampleProtoRequest.newBuilder()
                                                .setUserId("testUser")
                                                .setMessage(
                                                  String.format("[client] this is meesage from client, timestamp = %s"
                                                                            , System.currentTimeMillis()))
                                                .build();
    
    asyncStub.exampleCall(req, new StreamObserver<ExampleProtoResponse>(){

    @Override
    public void onNext(ExampleProtoResponse value) {
      log.info("[client]get message in client = {}", value);
    }

    @Override
    public void onError(Throwable t) {
      log.error("[client]error = {}", t.getMessage());
      t.printStackTrace();
    }

    @Override
    public void onCompleted() {
      log.info("[client]call onCompleted..");
    }
      
    });
  }
}
