package com.mv.hexagonal.payments.adapters.out.producer.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentInfoMessage {
  private Long id;
  private String status;
  private String statusDetail;
}
