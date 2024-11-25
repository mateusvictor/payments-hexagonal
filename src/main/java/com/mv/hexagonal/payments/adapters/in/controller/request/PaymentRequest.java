package com.mv.hexagonal.payments.adapters.in.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
  private BigDecimal amount;

  @JsonProperty("from_user_id")
  private Long fromUserId;

  @JsonProperty("to_user_id")
  private Long toUserId;

  @JsonProperty("idempotency_key")
  private String idempotencyKey;
}
