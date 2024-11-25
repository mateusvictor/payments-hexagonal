package com.mv.hexagonal.payments.application.core.usecase;

import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.InsufficientAmountException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.NotFoundException;
import com.mv.hexagonal.payments.application.ports.in.ExecutePaymentInputPort;
import com.mv.hexagonal.payments.application.ports.out.CreatePaymentOutputPort;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
import com.mv.hexagonal.payments.application.ports.out.TransferBalanceOutputPort;
import com.mv.hexagonal.payments.application.ports.out.UpdatePaymentOutputPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutePaymentUseCase implements ExecutePaymentInputPort {
  private final FindUserByIdOutputPort findUserByIdOutputPort;
  private final CreatePaymentOutputPort createPaymentOutputPort;
  private final UpdatePaymentOutputPort updatePaymentOutputPort;
  private final TransferBalanceOutputPort transferBalanceOutputPort;

  @Override
  public void execute(Payment newPayment) {
    var fromUser =
        findUserByIdOutputPort
            .find(newPayment.getFromUserId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "User from not found. ID: " + newPayment.getFromUserId()));

    var toUser =
        findUserByIdOutputPort
            .find(newPayment.getToUserId())
            .orElseThrow(
                () -> new NotFoundException("User to not found. ID: " + newPayment.getToUserId()));

    var paymentCreated = createNewPayment(newPayment);

    validateIfSenderHasEnoughBalance(fromUser, paymentCreated);

    transferBalanceOutputPort.transfer(fromUser, toUser, paymentCreated.getAmount());

    updatePayment(paymentCreated);
  }

  private void validateIfSenderHasEnoughBalance(User fromUser, Payment payment) {
    if (payment.getAmount().compareTo(fromUser.getBalance()) > 0) {
      throw new InsufficientAmountException(fromUser.getId(), payment.getAmount());
    }
  }

  private Payment createNewPayment(Payment payment) {
    payment.setStatus("CREATED");
    payment.setStatusDetail("CONFIRMATION_REQUIRED");
    return createPaymentOutputPort.create(payment);
  }

  private void updatePayment(Payment payment) {
    payment.setStatus("APPROVED");
    payment.setStatusDetail("APPROVED");
    updatePaymentOutputPort.update(payment);
  }
}
