package com.mv.hexagonal.payments.application.core.usecase;

import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.core.domain.User;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.FraudValidationException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.InsufficientAmountException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.InvalidUsersInvolvedException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.NotFoundException;
import com.mv.hexagonal.payments.application.core.usecase.exceptions.TransferBalanceException;
import com.mv.hexagonal.payments.application.ports.in.ExecutePaymentInputPort;
import com.mv.hexagonal.payments.application.ports.out.CreatePaymentOutputPort;
import com.mv.hexagonal.payments.application.ports.out.FindUserByIdOutputPort;
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

  @Override
  public void execute(Payment newPayment) {
    var fromUser = getUserOrThrowException(newPayment.getFromUserId());
    var toUser = getUserOrThrowException(newPayment.getToUserId());

    validateIfSenderAndReceiverAreNotTheSame(fromUser, toUser);

    var paymentCreated = createPaymentOutputPort.create(newPayment);

    validateAndExecuteTransaction(fromUser, toUser, paymentCreated);
  }

  private void validateAndExecuteTransaction(User fromUser, User toUser, Payment paymentCreated) {
    try {
      validateIfSenderHasEnoughBalance(fromUser, paymentCreated);
      validateIfTransactionIsSafe(paymentCreated);
      transferBalanceOutputPort.transfer(fromUser, toUser, paymentCreated.getAmount());
      paymentCreated.approve();

    } catch (FraudValidationException ex) {
      paymentCreated.cancelByFraud();
    } catch (InsufficientAmountException ex) {
      paymentCreated.cancelByInsufficientAmount();
    } catch (TransferBalanceException ex) {
      paymentCreated.cancelByError();
    } catch (Exception ex) {
      log.error("Error while executing payment", ex);
      paymentCreated.cancelByError();
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
}
