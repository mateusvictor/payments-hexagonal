package com.mv.hexagonal.payments.adapters.in.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponse {
  private Long id;
  private BigDecimal amount;

  @JsonProperty("from_user_id")
  private Long fromUserId;

  @JsonProperty("to_user_id")
  private Long toUserId;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  private String status;

  @JsonProperty("status_detail")
  private String statusDetail;
}
