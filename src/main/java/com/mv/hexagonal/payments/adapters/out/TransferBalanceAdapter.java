package com.mv.hexagonal.payments.adapters.out;

import com.mv.hexagonal.payments.adapters.out.repository.UserRepository;
import com.mv.hexagonal.payments.adapters.out.repository.mapper.UserEntityMapper;
import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.ports.out.TransferBalanceOutputPort;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransferBalanceAdapter implements TransferBalanceOutputPort {
  private final UserRepository userRepository;
  private final UserEntityMapper userEntityMapper;

  @Override
  @Transactional
  public void transfer(User fromUser, User toUser, BigDecimal amount) {
    fromUser.setBalance(fromUser.getBalance().subtract(amount));
    toUser.setBalance(toUser.getBalance().add(amount));

    var userEntityFrom = userEntityMapper.toUserEntity(fromUser);
    var userEntityTo = userEntityMapper.toUserEntity(toUser);

    userRepository.save(userEntityFrom);
    userRepository.save(userEntityTo);
  }
}
