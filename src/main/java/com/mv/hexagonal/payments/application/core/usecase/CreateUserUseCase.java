package com.mv.hexagonal.payments.application.core.usecase;

import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.ports.in.CreateUserInputPort;
import com.mv.hexagonal.payments.application.ports.out.CreateUserOutputPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserInputPort {
    private final CreateUserOutputPort createUserOutputPort;

    @Override
    public void create(User user) {
        createUserOutputPort.create(user);
    }
}
