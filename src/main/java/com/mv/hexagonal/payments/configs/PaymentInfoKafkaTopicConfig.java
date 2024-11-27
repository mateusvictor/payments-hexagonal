package com.mv.hexagonal.payments.configs;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class PaymentInfoKafkaTopicConfig {

  @Value(value = "${topics.bootstrap-address}")
  private String bootstrapAddress;

  @Value(value = "${topics.payment-info.name}")
  private String paymentInfoTopic;

  @Value(value = "${topics.payment-info.partitions}")
  private Integer partitions;

  @Value(value = "${topics.payment-info.replication-factor}")
  private Short replicationFactor;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean("paymentInfoTopic")
  public NewTopic locationTopic() {
    return new NewTopic(paymentInfoTopic, partitions, replicationFactor);
  }
}
