package com.mv.hexagonal.payments.application.core.usecase;

import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.BadRequestException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.FailedDependencyException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.FraudValidationException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.InsufficientAmountException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.InvalidUsersInvolvedException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.NotFoundException;
import com.mv.hexagonal.payments.application.ports.in.ExecutePaymentInputPort;
import com.mv.hexagonal.payments.application.ports.out.CreatePaymentOutputPort;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
import com.mv.hexagonal.payments.application.ports.out.PaymentInfoProducerOutputPort;
import com.mv.hexagonal.payments.application.ports.out.TransferBalanceOutputPort;
import com.mv.hexagonal.payments.application.ports.out.UpdatePaymentOutputPort;
import com.mv.hexagonal.payments.application.ports.out.ValidateTransactionByFraudOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ExecutePaymentUseCase implements ExecutePaymentInputPort {
  private final FindUserByIdOutputPort findUserByIdOutputPort;
  private final CreatePaymentOutputPort createPaymentOutputPort;
  private final UpdatePaymentOutputPort updatePaymentOutputPort;
  private final TransferBalanceOutputPort transferBalanceOutputPort;
  private final ValidateTransactionByFraudOutputPort validateTransactionByFraudOutputPort;
  private final PaymentInfoProducerOutputPort publishPaymentInfoOutputPort;

  @Override
  public Payment execute(Payment newPayment) {
    var fromUser = getUserOrThrowException(newPayment.getFromUserId());
    var toUser = getUserOrThrowException(newPayment.getToUserId());

    validateIfSenderAndReceiverAreNotTheSame(fromUser, toUser);

    var paymentCreated = createPaymentOutputPort.create(newPayment);

    validateTransaction(fromUser, paymentCreated);
    executeTransaction(fromUser, toUser, paymentCreated);

    publishPaymentInfo(paymentCreated);

    return paymentCreated;
  }

  private void executeTransaction(User fromUser, User toUser, Payment paymentCreated) {
    try {
      transferBalanceOutputPort.transfer(fromUser, toUser, paymentCreated.getAmount());
      paymentCreated.approve();
    } catch (Exception ex) {
      log.error("Error while executing payment", ex);
      paymentCreated.cancelByError();
      throw new FailedDependencyException("Error while executing payment", paymentCreated);
    } finally {
      updatePaymentOutputPort.update(paymentCreated);
    }
  }

  private void validateTransaction(User fromUser, Payment paymentCreated) {
    try {
      validateIfSenderHasEnoughBalance(fromUser, paymentCreated);
      validateIfTransactionIsSafe(paymentCreated);
    } catch (FraudValidationException ex) {
      paymentCreated.cancelByFraud();
      throw new BadRequestException("Fraud detected", paymentCreated);
    } catch (InsufficientAmountException ex) {
      paymentCreated.cancelByInsufficientAmount();
      throw new BadRequestException("Insufficient amount", paymentCreated);
    } finally {
      updatePaymentOutputPort.update(paymentCreated);
    }
  }

  private User getUserOrThrowException(Long userId) {
    return findUserByIdOutputPort
        .find(userId)
        .orElseThrow(() -> new NotFoundException("User not found. ID: " + userId));
  }

  private void validateIfSenderAndReceiverAreNotTheSame(User fromUser, User toUser) {
    if (fromUser.getId().equals(toUser.getId())) {
      throw new InvalidUsersInvolvedException();
    }
  }

  private void validateIfSenderHasEnoughBalance(User fromUser, Payment payment) {
    if (payment.getAmount().compareTo(fromUser.getBalance()) > 0) {
      throw new InsufficientAmountException(fromUser.getId(), payment.getAmount());
    }
  }

  private void validateIfTransactionIsSafe(Payment payment) {
    validateTransactionByFraudOutputPort.validate(payment);
  }

  private void publishPaymentInfo(Payment payment) {
    publishPaymentInfoOutputPort.publish(payment);
  }
}
