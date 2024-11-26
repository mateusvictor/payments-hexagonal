package com.mv.hexagonal.payments.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Payment {
  private Long id;
  private BigDecimal amount;
  private Long fromUserId;
  private Long toUserId;
  private String idempotencyKey;
  private LocalDateTime createdAt;
  private String status;
  private String statusDetail;

  public Payment() {
    this.status = "PENDING";
    this.statusDetail = "WAITING_APPROVAL";
  }

  public void approve() {
    this.setStatus("APPROVED");
    this.setStatusDetail("APPROVED");
  }

  public void cancelByFraud() {
    this.setStatus("CANCELLED");
    this.setStatusDetail("BY_FRAUD");
  }

  public void cancelByError() {
    this.setStatus("CANCELLED");
    this.setStatusDetail("BY_ERROR");
  }

  public void cancelByInsufficientAmount() {
    this.setStatus("CANCELLED");
    this.setStatusDetail("INSUFFICIENT_AMOUNT");
  }

  public void refundByAdmin() {
    this.setStatus("REFUNDED");
    this.setStatusDetail("BY_ADMIN");
  }
}
