package io.github.rura6502;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.function.Consumer;

/**
 * @author abcd
 * @since 2021-06-30
 */
@Getter
@Slf4j
public class KafkaTestContainer {

  public KafkaContainer kafkaContainer;

  public KafkaTestContainer() {
    kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.0"));
  }

  public void run(Consumer<KafkaContainer> runningListener) {
    log.info("run kafka container...");
    this.kafkaContainer.start();

    while (true) {
      if (this.kafkaContainer.isRunning()) {
        log.info("kafka container is running...");
        log.info("= kafkaContainer.getContainerId() = {}", kafkaContainer.getContainerId());
        log.info("= kafkaContainer.getHost() = {}", kafkaContainer.getHost());
        log.info("= kafkaContainer.getBootstrapServers() = {}", kafkaContainer.getBootstrapServers());
        runningListener.accept(this.kafkaContainer);
        break;
      }
      log.info("kafka container is not running now...");
    }
  }
}