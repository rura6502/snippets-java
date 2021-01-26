package io.github.rura6502.basic.server;

import org.springframework.stereotype.Service;

import io.github.rura6502.basic.ExampleProtoRequest;
import io.github.rura6502.basic.ExampleProtoResponse;
import io.github.rura6502.basic.ExampleServiceGrpc.ExampleServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExampleServiceImpl extends ExampleServiceImplBase {

  @Override
  public void exampleCall(ExampleProtoRequest request, StreamObserver<ExampleProtoResponse> responseObserver) {
    
    log.info("[server] userId = {}, message = {}", request.getUserId(), request.getMessage());
    ExampleProtoResponse exampleProtoResponse
      = ExampleProtoResponse.newBuilder()
            .setMessage("[server] this is server response...")
            .build();
    
    responseObserver.onNext(exampleProtoResponse);
    responseObserver.onCompleted();

  }

  

  

  
}
