package com.mv.hexagonal.payments.configs;

import com.mv.hexagonal.payments.application.core.usecase.CreateUserUseCase;
import com.mv.hexagonal.payments.application.ports.out.CreateUserOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUserConfig {
    @Bean
    public CreateUserUseCase createUserUseCase(CreateUserOutputPort createUserAdapter) {
        return new CreateUserUseCase(createUserAdapter);
    }
}
