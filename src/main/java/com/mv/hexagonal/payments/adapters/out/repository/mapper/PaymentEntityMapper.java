package com.mv.hexagonal.payments.adapters.out.repository.mapper;

import com.mv.hexagonal.payments.adapters.out.repository.entity.PaymentEntity;
import com.mv.hexagonal.payments.application.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {
  PaymentEntity toPaymentEntity(Payment payment);

  Payment toPayment(PaymentEntity paymentEntity);
}
