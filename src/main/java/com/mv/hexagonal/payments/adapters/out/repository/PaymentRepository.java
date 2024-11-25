package com.mv.hexagonal.payments.adapters.out.repository;

import com.mv.hexagonal.payments.adapters.out.repository.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {}
