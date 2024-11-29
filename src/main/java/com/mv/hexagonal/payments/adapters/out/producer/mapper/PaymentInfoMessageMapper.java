package com.mv.hexagonal.payments.adapters.out.producer.mapper;

import com.mv.hexagonal.payments.adapters.out.producer.message.PaymentInfoMessage;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentInfoMessageMapper {
  PaymentInfoMessage toPaymentInfoMessage(Payment payment);

  Payment toPayment(PaymentInfoMessage paymentInfoMessage);
}
