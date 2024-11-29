package com.mv.hexagonal.payments.configs;

import com.mv.hexagonal.payments.application.core.usecase.SendNotificationGivenPaymentUseCase;
import com.mv.hexagonal.payments.application.ports.in.SendNotificationGivenPaymentInput;
import com.mv.hexagonal.payments.application.ports.out.SendNotificationOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendNotificationConfig {
  @Bean
  public SendNotificationGivenPaymentInput sendNotificationGivenPaymentInput(
      SendNotificationOutputPort sendNotificationOutputPort) {
    return new SendNotificationGivenPaymentUseCase(sendNotificationOutputPort);
  }
}
