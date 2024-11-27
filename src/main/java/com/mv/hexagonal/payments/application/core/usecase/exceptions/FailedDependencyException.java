package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import com.mv.hexagonal.payments.application.core.domain.Payment;
import org.springframework.http.HttpStatus;

public class FailedDependencyException extends ApiException {
  public FailedDependencyException(String description, Payment payment) {
    super(
        HttpStatus.FAILED_DEPENDENCY.getReasonPhrase(),
        description,
        HttpStatus.FAILED_DEPENDENCY.value());
  }
}
