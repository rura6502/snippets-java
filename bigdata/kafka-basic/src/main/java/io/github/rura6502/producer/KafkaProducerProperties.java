package io.github.rura6502.producer;

import lombok.Getter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author abcd
 * @since 2021-06-30
 */
@Getter
public class KafkaProducerProperties {

  private String bootstrapServer;

  private String keySerializer = StringSerializer.class.getName();

  private String valueSerializer = StringSerializer.class.getName();

  public KafkaProducerProperties(String bootstrapServer) {
    this.bootstrapServer = bootstrapServer;
  }

  public KafkaProducerProperties(String bootstrapServer, String keySerializer, String valueSerializer) {
    this.bootstrapServer = bootstrapServer;
    this.keySerializer = keySerializer;
    this.valueSerializer = valueSerializer;
  }

  public Properties asProperties() {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
    return properties;
  }
}
