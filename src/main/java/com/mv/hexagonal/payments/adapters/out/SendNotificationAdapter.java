package com.mv.hexagonal.payments.adapters.out;

import static com.mv.hexagonal.payments.application.core.domain.NotificationType.AMOUNT_RECEIVED;

import com.mv.hexagonal.payments.adapters.out.client.NotificationsClient;
import com.mv.hexagonal.payments.adapters.out.client.request.NotificationRequest;
import com.mv.hexagonal.payments.application.core.domain.NotificationType;
import com.mv.hexagonal.payments.application.ports.out.SendNotificationOutputPort;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SendNotificationAdapter implements SendNotificationOutputPort {
  private final NotificationsClient notificationsClient;

  @Override
  public void send(Long userId, BigDecimal amount, NotificationType notificationType) {
    var request = mapNotificationRequest(userId, amount, notificationType);
    log.info("Sending notification for userId {}", userId);
    notificationsClient.send(request);
  }

  private NotificationRequest mapNotificationRequest(
      Long userId, BigDecimal amount, NotificationType notificationType) {
    return NotificationRequest.builder()
        .userId(userId)
        .content(getText(notificationType, amount))
        .build();
  }

  private String getText(NotificationType notificationType, BigDecimal amount) {
    return AMOUNT_RECEIVED.equals(notificationType)
        ? String.format("You received $ %s.", amount)
        : String.format("You sent $ %s. ", amount);
  }
}
