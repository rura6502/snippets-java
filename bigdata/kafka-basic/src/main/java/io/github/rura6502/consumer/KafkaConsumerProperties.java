package io.github.rura6502.consumer;

import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @author abcd
 * @since 2021-06-30
 */
@Getter
public class KafkaConsumerProperties {

  private final String bootstrapServer;

  private final String topicName;

  private String keySerializer = StringDeserializer.class.getName();

  private String valueSerializer = StringDeserializer.class.getName();

  public KafkaConsumerProperties(String bootstrapServer, String topicName) {
    this.bootstrapServer = bootstrapServer;
    this.topicName = topicName;
  }

  public KafkaConsumerProperties(String bootstrapServer, String topicName, String keySerializer, String valueSerializer) {
    this.bootstrapServer = bootstrapServer;
    this.topicName = topicName;
    this.keySerializer = keySerializer;
    this.valueSerializer = valueSerializer;
  }

  public Properties asProperties() {
    Properties properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keySerializer);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSerializer);
    return properties;
  }
}

