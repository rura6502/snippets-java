package io.github.rura6502;

import io.github.rura6502.consumer.KafkaConsumerProperties;
import io.github.rura6502.consumer.SimpleConsumer;
import io.github.rura6502.producer.KafkaProducerProperties;
import io.github.rura6502.producer.SimpleProducer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaBasic {

  @SneakyThrows
  public static void main(String[] args) {

    log.error("start...");


    KafkaTestContainer kafka = new KafkaTestContainer();

    final String topic = "example-topic";



    kafka.run(kafkaContainer -> {
      KafkaConsumerProperties cProp =
        new KafkaConsumerProperties(kafkaContainer.getBootstrapServers(), topic);
      KafkaProducerProperties pProp =
        new KafkaProducerProperties(kafkaContainer.getBootstrapServers());
      new SimpleConsumer(cProp).run();
      new SimpleProducer(topic, pProp).run();
    });
  }
}
