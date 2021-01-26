package io.github.rura6502.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.rura6502.basic.client.GrpcClient;

@SpringBootApplication
public class BasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(BasicApplication.class, args);
  }

  @Autowired
  private GrpcClient client;

  @Bean
  ApplicationRunner runner() {
    return args -> {

      new Thread(() -> {
        while (true) {
          client.exampleCall();

          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    };
  }

}
