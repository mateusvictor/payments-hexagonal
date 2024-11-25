package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.Payment;

public interface UpdatePaymentOutputPort {
  void update(Payment payment);
}
