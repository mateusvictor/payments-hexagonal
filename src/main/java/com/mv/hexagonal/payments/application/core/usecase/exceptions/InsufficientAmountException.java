package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;

public class InsufficientAmountException extends ApiException {
  private static final String MESSAGE =
      "User has not enough money to perform operation. UserID: %s. Operation amount: %s";

  public InsufficientAmountException(Long userId, BigDecimal amount) {
    super(
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        String.format(MESSAGE, userId, amount),
        HttpStatus.BAD_REQUEST.value());
  }
}
