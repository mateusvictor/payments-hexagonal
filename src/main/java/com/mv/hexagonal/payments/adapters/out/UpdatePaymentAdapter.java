package com.mv.hexagonal.payments.adapters.out;

import com.mv.hexagonal.payments.adapters.out.repository.PaymentRepository;
import com.mv.hexagonal.payments.adapters.out.repository.mapper.PaymentEntityMapper;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import com.mv.hexagonal.payments.application.ports.out.UpdatePaymentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePaymentAdapter implements UpdatePaymentOutputPort {
  private final PaymentRepository paymentRepository;
  private final PaymentEntityMapper paymentEntityMapper;

  @Override
  public Payment update(Payment payment) {
    var paymentEntity = paymentEntityMapper.toPaymentEntity(payment);
    return paymentEntityMapper.toPayment(paymentRepository.save(paymentEntity));
  }
}
