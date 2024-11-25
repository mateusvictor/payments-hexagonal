package com.mv.hexagonal.payments.adapters.in.controller.handler;

import com.mv.hexagonal.payments.adapters.in.controller.response.ApiError;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    log.info("MethodArgumentNotValidException: {}", ex.toString());
    var apiError =
        ApiError.builder()
            .error(ex.getBody().getTitle())
            .code("PH-01")
            .message(ex.getBody().getDetail())
            .status(HttpStatus.BAD_REQUEST.value())
            .build();
    return ResponseEntity.badRequest().body(apiError);
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<?> handleApiException(ApiException ex) {
    log.info("ApiException: {}", ex.toString());
    var apiError =
        ApiError.builder()
            .code(ex.getCode())
            .message(ex.getDescription())
            .status(ex.getStatusCode())
            .build();
    return ResponseEntity.status(ex.getStatusCode()).body(apiError);
  }
}
