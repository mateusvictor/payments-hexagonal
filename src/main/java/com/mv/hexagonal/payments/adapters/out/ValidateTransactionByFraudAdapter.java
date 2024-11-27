package com.mv.hexagonal.payments.adapters.out;

import com.mv.hexagonal.payments.adapters.out.client.FraudTransactionClient;
import com.mv.hexagonal.payments.adapters.out.client.mapper.FraudTransactionMapper;
import com.mv.hexagonal.payments.adapters.out.client.response.FraudTransactionResponse;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.FraudValidationException;
import com.mv.hexagonal.payments.application.ports.out.ValidateTransactionByFraudOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class ValidateTransactionByFraudAdapter implements ValidateTransactionByFraudOutputPort {
  private final FraudTransactionClient fraudTransactionClient;
  private final FraudTransactionMapper fraudTransactionMapper;

  @Override
  public void validate(Payment payment) throws FraudValidationException {
    var fraudTransactionRequest = fraudTransactionMapper.toFraudTransactionRequest(payment);
    var response = (FraudTransactionResponse) null;

    try {
      response = fraudTransactionClient.validate(fraudTransactionRequest);
    } catch (Exception ex) {
      log.error("Error while validating transaction by fraud", ex);
      throw new FraudValidationException(
          payment.getFromUserId(), payment.getToUserId(), payment.getAmount());
    }

    if (!response.isValid()) {
      throw new FraudValidationException(
          payment.getFromUserId(), payment.getToUserId(), payment.getAmount());
    }
  }
}
