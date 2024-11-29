package com.mv.hexagonal.payments.adapters.out.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudTransactionRequest {
  private BigDecimal amount;

  @JsonProperty("from_user_id")
  private Long fromUserId;

  @JsonProperty("to_user_id")
  private Long toUserId;

  @JsonProperty("idempotency_key")
  private String idempotencyKey;
}
