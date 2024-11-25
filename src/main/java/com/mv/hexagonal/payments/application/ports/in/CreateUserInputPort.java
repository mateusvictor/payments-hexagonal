package com.mv.hexagonal.payments.application.ports.in;

import com.mv.hexagonal.payments.application.core.domain.User;

public interface CreateUserInputPort {
    void create(User user);
}
