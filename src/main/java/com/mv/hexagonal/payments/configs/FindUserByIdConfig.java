package com.mv.hexagonal.payments.configs;

import com.mv.hexagonal.payments.application.core.usecase.FindUserByIdUseCase;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindUserByIdConfig {
  @Bean
  public FindUserByIdUseCase findUserByIdUseCase(FindUserByIdOutputPort findUserByIdOutputPort) {
    return new FindUserByIdUseCase(findUserByIdOutputPort);
  }
}
