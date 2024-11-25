package com.mv.hexagonal.payments.application.core.usecase.exceptions;

import lombok.Getter;

/** Exception containing relevant information for API errors. */
@Getter
public class ApiException extends RuntimeException {
  private final String code;

  private final String description;

  private final Integer statusCode;

  /**
   * Creates a new instance, with provided fields.
   *
   * @param code API error code.
   * @param description API error description.
   * @param statusCode API error HTTP Status code.
   */
  public ApiException(final String code, final String description, final Integer statusCode) {
    super(description);
    this.code = code;
    this.description = description;
    this.statusCode = statusCode;
  }

  /**
   * Creates a new instance, with provided fields.
   *
   * @param code API error code.
   * @param description API error description.
   * @param statusCode API error HTTP Status code.
   * @param cause API error cause.
   */
  public ApiException(
      final String code,
      final String description,
      final Integer statusCode,
      final Throwable cause) {
    super(description, cause);
    this.code = code;
    this.description = description;
    this.statusCode = statusCode;
  }
}
