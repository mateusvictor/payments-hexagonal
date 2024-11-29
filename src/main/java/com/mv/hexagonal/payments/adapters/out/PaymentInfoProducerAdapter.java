package com.mv.hexagonal.payments.adapters.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mv.hexagonal.payments.adapters.out.producer.mapper.PaymentInfoMessageMapper;
import com.mv.hexagonal.payments.adapters.out.producer.message.PaymentInfoMessage;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.ports.out.PaymentInfoProducerOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentInfoProducerAdapter implements PaymentInfoProducerOutputPort {
  private final PaymentInfoMessageMapper paymentInfoMessageMapper;
  private final KafkaTemplate<String, PaymentInfoMessage> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Value(value = "${topics.payment-info.name}")
  private String paymentInfoTopic;

  @Override
  public void publish(Payment payment) {
    var paymentMessage = paymentInfoMessageMapper.toPaymentInfoMessage(payment);

    try {
      var key = String.valueOf(paymentMessage.getId());

      log.info(
          String.format(
              "Start sending message with key: %s and value: %s for topic: %s",
              key, paymentMessage, paymentInfoTopic));

      this.kafkaTemplate.send(paymentInfoTopic, key, paymentMessage);
    } catch (Exception e) {
      log.error("Kafka producer error: ", e);
    }
  }
}
