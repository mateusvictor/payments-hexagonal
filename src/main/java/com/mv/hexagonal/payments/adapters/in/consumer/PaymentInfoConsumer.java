package com.mv.hexagonal.payments.adapters.in.consumer;

import com.mv.hexagonal.payments.adapters.out.producer.mapper.PaymentInfoMessageMapper;
import com.mv.hexagonal.payments.adapters.out.producer.message.PaymentInfoMessage;
import com.mv.hexagonal.payments.application.ports.in.SendNotificationGivenPaymentInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PaymentInfoConsumer {
  private final PaymentInfoMessageMapper paymentInfoMessageMapper;
  private final SendNotificationGivenPaymentInput sendNotificationGivenPayment;

  @KafkaListener(
      topics = "${topics.payment-info.name}",
      groupId = "${topics.payment-info.group-id}",
      containerFactory = "paymentInfoKafkaListenerContainerFactory")
  public void receive(ConsumerRecord<String, PaymentInfoMessage> consumerRecord) {
    log.info("ConsumerRecord: {}", consumerRecord);

    var payment = paymentInfoMessageMapper.toPayment(consumerRecord.value());

    sendNotificationGivenPayment.send(payment);
  }
}
