package com.mv.hexagonal.payments.adapters.out.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudTransactionResponse {
  private boolean valid;
}
