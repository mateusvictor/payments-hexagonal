package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.TransferBalanceException;
import java.math.BigDecimal;

public interface TransferBalanceOutputPort {
  void transfer(User fromUser, User toUser, BigDecimal amount) throws TransferBalanceException;
}
