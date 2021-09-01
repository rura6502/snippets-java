package io.github.rura6502.consumer;

import io.github.rura6502.producer.SimpleProducer;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

/**
 * @author abcd
 * @since 2021-06-30
 */
@Getter
@Slf4j
public class SimpleConsumer {

  private final KafkaConsumerProperties kafkaConsumerProperties;

  private KafkaConsumer<String, String> consumer;

  public SimpleConsumer(KafkaConsumerProperties kafkaConsumerProperties) {
    this.kafkaConsumerProperties = kafkaConsumerProperties;
  }

  @SneakyThrows
  public void run() {
    log.info("start consumer...");
    consumer = new KafkaConsumer<String, String>(kafkaConsumerProperties.asProperties());
    consumer.subscribe(Collections.singleton(kafkaConsumerProperties.getTopicName()));

    new Thread(() -> {

      int count = 0;

      while (true) {

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));

        if (records.isEmpty()) {
          log.info("no data...");
        }

        count += records.count();
        for (ConsumerRecord<String, String> record : records) {
          log.info("received : {}", record.value());

          if (count == SimpleProducer.sentDataSize) {
            log.info("received all data...");
            break;
          }
        }
      }
    }).start();
  }
}
