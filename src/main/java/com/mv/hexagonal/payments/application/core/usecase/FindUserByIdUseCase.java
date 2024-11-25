package com.mv.hexagonal.payments.application.core.usecase;

import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.NotFoundException;
import com.mv.hexagonal.payments.application.ports.in.FindUserByIdInputPort;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUserByIdUseCase implements FindUserByIdInputPort {
  private final FindUserByIdOutputPort findUserByIdOutputPort;

  @Override
  public User find(Long id) {
    return findUserByIdOutputPort
        .find(id)
        .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
  }
}
