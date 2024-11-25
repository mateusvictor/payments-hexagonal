package com.mv.hexagonal.payments.adapters.out;

import com.mv.hexagonal.payments.adapters.out.repository.UserRepository;
import com.mv.hexagonal.payments.adapters.out.repository.mapper.UserEntityMapper;
import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserByIdAdapter implements FindUserByIdOutputPort {
  private final UserRepository userRepository;
  private final UserEntityMapper userEntityMapper;

  @Override
  public Optional<User> find(Long id) {
    var userEntity = userRepository.findById(id);
    return userEntity.map(userEntityMapper::toUser);
  }
}
