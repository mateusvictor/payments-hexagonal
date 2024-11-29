package com.mv.hexagonal.payments.adapters.out.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class NotificationRequest {
  private String content;

  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("idempotency_key")
  private Long idempotencyKey;
}
