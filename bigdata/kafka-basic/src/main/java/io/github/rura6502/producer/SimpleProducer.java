package io.github.rura6502.producer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author abcd
 * @since 2021-06-30
 */
@Getter
@Slf4j
public class SimpleProducer {

  private final String topicName;

  private final KafkaProducerProperties kafkaProducerProperties;

  private KafkaProducer<String, String> producer;

  public static int count = 0;

  public static int sentDataSize = Integer.MAX_VALUE;

  public SimpleProducer(String topicName, KafkaProducerProperties kafkaProducerProperties) {
    this.topicName = topicName;
    this.kafkaProducerProperties = kafkaProducerProperties;
  }

  public void run() {
    log.info("start producer...");
    producer = new KafkaProducer<String, String>(kafkaProducerProperties.asProperties());
    new Thread(() -> {
      final long start = System.currentTimeMillis();
      while (true) {
        final String date = LocalDateTime.now().toString();
        ProducerRecord<String, String> record =
          new ProducerRecord<>(topicName, count + "");
        producer.send(record);
        log.info("sent : {}", count);

//        if (count % 10 == 0) {
//          System.out.println(Thread.currentThread().getName() + "][producer] sent data until " + count);
//        }

        if (count == sentDataSize) {
          log.info("done...");
          break;
        }
        count++;

        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      log.info("spend : {}", (System.currentTimeMillis() - start)/1000L + "s");
    }).start();
  }
}
