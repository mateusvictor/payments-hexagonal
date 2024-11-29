package com.mv.hexagonal.payments.adapters.out.producer.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoMessage {
  private Long id;
  private BigDecimal amount;

  @JsonProperty("from_user_id")
  private Long fromUserId;

  @JsonProperty("to_user_id")
  private Long toUserId;

  @JsonProperty("idempotency_key")
  private String idempotencyKey;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("status")
  private String status;

  @JsonProperty("status_detail")
  private String statusDetail;
}
