package com.mv.hexagonal.payments.adapters.out;

import com.mv.hexagonal.payments.adapters.out.repository.UserRepository;
import com.mv.hexagonal.payments.adapters.out.repository.mapper.UserEntityMapper;
import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.ports.out.CreateUserOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateUserAdapter implements CreateUserOutputPort {
  private final UserRepository userRepository;
  private final UserEntityMapper userEntityMapper;

  @Override
  public void create(User user) {
    var userEntity = userEntityMapper.toUserEntity(user);
    userRepository.save(userEntity);
  }
}
