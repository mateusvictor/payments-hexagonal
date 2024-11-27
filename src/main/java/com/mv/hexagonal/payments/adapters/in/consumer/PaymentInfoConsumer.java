package com.mv.hexagonal.payments.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PaymentInfoConsumer {
  @KafkaListener(
      topics = "${topics.payment-info.name}",
      groupId = "${topics.payment-info.group-id}")
  public void receive(ConsumerRecord<String, String> consumerRecord) {
    log.info("Received payment info: {}", consumerRecord);
    log.info("consumer key: {}", consumerRecord.key());
    log.info("consumer value: {}", consumerRecord.value());
  }
}
