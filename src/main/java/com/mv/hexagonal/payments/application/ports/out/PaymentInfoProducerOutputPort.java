package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.Payment;

public interface PaymentInfoProducerOutputPort {
  void publish(Payment payment);
}
