package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.FraudValidationException;

public interface ValidateTransactionByFraudOutputPort {
  void validate(Payment payment) throws FraudValidationException;
}
