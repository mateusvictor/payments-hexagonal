package com.mv.hexagonal.payments.adapters.out.client.mapper;

import com.mv.hexagonal.payments.adapters.out.client.request.FraudTransactionRequest;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FraudTransactionMapper {
  FraudTransactionRequest toFraudTransactionRequest(Payment payment);
}
