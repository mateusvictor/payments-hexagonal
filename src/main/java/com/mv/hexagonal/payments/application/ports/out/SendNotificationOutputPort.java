package com.mv.hexagonal.payments.application.ports.out;

import com.mv.hexagonal.payments.application.core.domain.NotificationType;
import java.math.BigDecimal;

public interface SendNotificationOutputPort {
  void send(Long userId, BigDecimal amount, NotificationType notificationType);
}
