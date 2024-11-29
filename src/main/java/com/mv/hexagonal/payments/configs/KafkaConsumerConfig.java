package com.mv.hexagonal.payments.configs;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

import com.mv.hexagonal.payments.adapters.out.producer.message.PaymentInfoMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  @Value(value = "${topics.bootstrap-address}")
  private String bootstrapAddress;

  @Value(value = "${topics.payment-info.group-id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, PaymentInfoMessage> paymentInfoConsumerFactory() {
    Map<String, Object> props = new HashMap<>();

    props.put(BOOTSTRAP_SERVERS_CONFIG, this.bootstrapAddress);
    props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    //    props.put(GROUP_ID_CONFIG, groupId);

    //    return new DefaultKafkaConsumerFactory<>(props);
    return new DefaultKafkaConsumerFactory<>(
        props, new StringDeserializer(), new JsonDeserializer<>(PaymentInfoMessage.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, PaymentInfoMessage>
      paymentInfoKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, PaymentInfoMessage> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(paymentInfoConsumerFactory());
    return factory;
  }
}
