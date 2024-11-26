package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUsersInvolvedException extends ApiException {
  private static final String MESSAGE = "Sender and receiver cannot be the same user";

  public InvalidUsersInvolvedException() {
    super(HttpStatus.BAD_REQUEST.getReasonPhrase(), MESSAGE, HttpStatus.BAD_REQUEST.value());
  }
}
