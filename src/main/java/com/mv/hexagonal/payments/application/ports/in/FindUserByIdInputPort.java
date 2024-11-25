package com.mv.hexagonal.payments.application.ports.in;

import com.mv.hexagonal.payments.application.core.domain.User;

public interface FindUserByIdInputPort {
  User find(Long id);
}
