package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import com.mv.hexagonal.payments.application.core.domain.Payment;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends ApiException {
  private final Payment payment;

  public BadRequestException(String description, Payment payment) {
    super(HttpStatus.BAD_REQUEST.getReasonPhrase(), description, HttpStatus.BAD_REQUEST.value());
    this.payment = payment;
  }
}
