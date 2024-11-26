package com.mv.hexagonal.payments.configs;

import com.mv.hexagonal.payments.application.core.usecase.ExecutePaymentUseCase;
import com.mv.hexagonal.payments.application.ports.out.CreatePaymentOutputPort;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
import com.mv.hexagonal.payments.application.ports.out.TransferBalanceOutputPort;
import com.mv.hexagonal.payments.application.ports.out.UpdatePaymentOutputPort;
import com.mv.hexagonal.payments.application.ports.out.ValidateTransactionByFraudOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutePaymentConfig {
  @Bean
  public ExecutePaymentUseCase executePaymentUseCase(
      FindUserByIdOutputPort findUserByIdOutputPort,
      CreatePaymentOutputPort createPaymentOutputPort,
      UpdatePaymentOutputPort updatePaymentOutputPort,
      TransferBalanceOutputPort transferBalanceOutputPort,
      ValidateTransactionByFraudOutputPort validateTransactionByFraudOutputPort) {
    return new ExecutePaymentUseCase(
        findUserByIdOutputPort,
        createPaymentOutputPort,
        updatePaymentOutputPort,
        transferBalanceOutputPort,
        validateTransactionByFraudOutputPort);
  }
}
