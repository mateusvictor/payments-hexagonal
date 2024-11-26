package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;

public class FraudValidationException extends ApiException {
  private static final String MESSAGE =
      "Operation was rejected by fraud validation - High Risk. UserID Sender: %s - UserID Receiver:"
          + " %s - Operation amount: %s";

  public FraudValidationException(Long sender, Long receiver, BigDecimal amount) {
    super(
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        String.format(MESSAGE, sender, receiver, amount),
        HttpStatus.BAD_REQUEST.value());
  }
}
