package com.mv.hexagonal.payments.adapters.out.repository;

import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.ports.out.CreateUserOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateUserAdapter implements CreateUserOutputPort {
    @Override
    public void create(User user) {
        log.info("User created. User: {}", user);
    }
}
