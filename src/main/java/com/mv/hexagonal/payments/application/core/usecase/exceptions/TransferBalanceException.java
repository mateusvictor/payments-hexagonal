package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;

public class TransferBalanceException extends ApiException {
  private static final String MESSAGE =
      "Error transferring balance. UserID Sender: %s - UserID Receiver: %s - Operation amount: %s";

  public TransferBalanceException(Long fromUserId, Long toUserId, BigDecimal amount) {
    super(
        HttpStatus.FAILED_DEPENDENCY.getReasonPhrase(),
        String.format(MESSAGE, fromUserId, toUserId, amount),
        HttpStatus.FAILED_DEPENDENCY.value());
  }
}
