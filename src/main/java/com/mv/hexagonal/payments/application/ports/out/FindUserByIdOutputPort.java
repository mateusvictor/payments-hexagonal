package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.User;
import java.util.Optional;

public interface FindUserByIdOutputPort {
  Optional<User> find(Long id);
}
