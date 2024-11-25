package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.User;

public interface CreateUserOutputPort {
    void create(User user);
}
