package com.mv.hexagonal.payments.adapters.out.client.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudTransactionRequest {
  private BigDecimal amount;
  private Long fromUserId;
  private Long toUserId;
  private String idempotencyKey;
}
