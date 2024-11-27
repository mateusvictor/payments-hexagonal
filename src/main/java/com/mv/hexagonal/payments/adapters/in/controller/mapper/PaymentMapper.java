package com.mv.hexagonal.payments.adapters.in.controller.mapper;

import com.mv.hexagonal.payments.adapters.in.controller.request.PaymentRequest;
import com.mv.hexagonal.payments.adapters.in.controller.response.PaymentResponse;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
  Payment toPayment(PaymentRequest paymentRequest);

  PaymentResponse toPaymentResponse(Payment payment);
}
