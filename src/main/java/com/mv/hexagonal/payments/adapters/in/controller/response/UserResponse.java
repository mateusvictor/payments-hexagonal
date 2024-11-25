package com.mv.hexagonal.payments.adapters.in.controller.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
  private Long id;
  private String name;
  private String email;
  private BigDecimal balance;
}
