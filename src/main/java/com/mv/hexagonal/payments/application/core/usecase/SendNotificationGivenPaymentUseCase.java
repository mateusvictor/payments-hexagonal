package com.mv.hexagonal.payments.application.core.usecase;

import com.mv.hexagonal.payments.application.core.domain.NotificationType;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.ApiException;
import com.mv.hexagonal.payments.application.ports.in.SendNotificationGivenPaymentInput;
import com.mv.hexagonal.payments.application.ports.out.SendNotificationOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Slf4j
public class SendNotificationGivenPaymentUseCase implements SendNotificationGivenPaymentInput {
  private final SendNotificationOutputPort sendNotificationOutputPort;

  @Override
  public void send(Payment payment) {
    if (!isPaymentApproved(payment)) {
      return;
    }

    try {
      sendNotificationToSender(payment);
      sendNotificationToReceiver(payment);
    } catch (Exception ex) {
      log.error("Error sending notification. Retrying", ex);
      throw new ApiException(
          HttpStatus.FAILED_DEPENDENCY.getReasonPhrase(),
          "Error sending notification.",
          HttpStatus.FAILED_DEPENDENCY.value());
    }
  }

  private void sendNotificationToSender(Payment payment) {
    sendNotificationOutputPort.send(
        payment.getFromUserId(), payment.getAmount(), NotificationType.AMOUNT_SENT);
  }

  private void sendNotificationToReceiver(Payment payment) {
    sendNotificationOutputPort.send(
        payment.getToUserId(), payment.getAmount(), NotificationType.AMOUNT_RECEIVED);
  }

  private boolean isPaymentApproved(Payment payment) {
    return "APPROVED".equalsIgnoreCase(payment.getStatus());
  }
}
