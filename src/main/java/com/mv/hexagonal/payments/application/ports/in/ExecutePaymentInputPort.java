package com.mv.hexagonal.payments.application.ports.in;

import com.mv.hexagonal.payments.application.core.domain.Payment;

public interface ExecutePaymentInputPort {
  void execute(Payment payment);
}
