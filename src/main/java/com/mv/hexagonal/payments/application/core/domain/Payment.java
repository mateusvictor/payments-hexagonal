package com.mv.hexagonal.payments.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
    private Long id;
    private BigDecimal amount;
    private Long fromUserId;
    private Long toUserId;
    private String authenticationCode;
    private LocalDateTime createdAt;
}
