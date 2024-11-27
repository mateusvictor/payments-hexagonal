package com.mv.hexagonal.payments.adapters.in.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
  @NotNull(message = "Balance is required")
  @Positive(message = "Balance must be greater than or equal to zero")
  private BigDecimal amount;

  @NotNull(message = "From User ID is required")
  @Positive(message = "From User ID must be greater than zero")
  @JsonProperty("from_user_id")
  private Long fromUserId;

  @NotNull(message = "To User ID is required")
  @Positive(message = "To User ID must be greater than zero")
  @JsonProperty("to_user_id")
  private Long toUserId;

  @NotBlank(message = "Idempotency Key is required")
  @JsonProperty("idempotency_key")
  private String idempotencyKey;
}
