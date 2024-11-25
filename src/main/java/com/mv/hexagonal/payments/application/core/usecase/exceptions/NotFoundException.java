package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
  public NotFoundException(String description) {
    super(HttpStatus.NOT_FOUND.getReasonPhrase(), description, HttpStatus.NOT_FOUND.value());
  }
}
