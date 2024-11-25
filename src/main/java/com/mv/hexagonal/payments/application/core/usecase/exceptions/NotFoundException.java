package com.mv.hexagonal.payments.application.core.usecase.exceptions;

public class NotFoundException extends ApiException {
  public NotFoundException(String description) {
    super("NOT_FOUND", description, 404);
  }
}
