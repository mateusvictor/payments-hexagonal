package com.mv.hexagonal.payments.adapters.out;

import com.mv.hexagonal.payments.adapters.out.client.FraudTransactionClient;
import com.mv.hexagonal.payments.adapters.out.client.mapper.FraudTransactionMapper;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.FraudValidationException;
import com.mv.hexagonal.payments.application.ports.out.ValidateTransactionByFraudOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidateTransactionByFraudAdapter implements ValidateTransactionByFraudOutputPort {
  private final FraudTransactionClient fraudTransactionClient;
  private final FraudTransactionMapper fraudTransactionMapper;

  @Override
  public void validate(Payment payment) throws FraudValidationException {
    var fraudTransactionRequest = fraudTransactionMapper.toFraudTransactionRequest(payment);
    var response = fraudTransactionClient.validate(fraudTransactionRequest);

    if (!response.isValid()) {
      throw new FraudValidationException(
          payment.getFromUserId(), payment.getToUserId(), payment.getAmount());
    }
  }
}
