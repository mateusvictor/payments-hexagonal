package com.mv.hexagonal.payments.adapters.in.controller;

import com.mv.hexagonal.payments.adapters.in.controller.mapper.PaymentMapper;
import com.mv.hexagonal.payments.adapters.in.controller.request.PaymentRequest;
import com.mv.hexagonal.payments.adapters.in.controller.response.PaymentResponse;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.ports.in.ExecutePaymentInputPort;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
  private final ExecutePaymentInputPort executePaymentInputPort;
  private final PaymentMapper paymentMapper;

  @PostMapping
  public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest paymentRequest) {
    var newPayment = paymentMapper.toPayment(paymentRequest);
    var result = executePaymentInputPort.execute(newPayment);

    return ResponseEntity.created(getCreatedUrl(result))
        .body(paymentMapper.toPaymentResponse(result));
  }

  private URI getCreatedUrl(Payment payment) {
    return URI.create("/api/v1/payments/" + payment.getId());
  }
}
