package com.mv.hexagonal.payments.adapters.out.client;

import com.mv.hexagonal.payments.adapters.out.client.request.FraudTransactionRequest;
import com.mv.hexagonal.payments.adapters.out.client.response.FraudTransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FraudTransactionClient", url = "${clients.fraud-api.url}")
public interface FraudTransactionClient {

  @PostMapping("/transactions/validate")
  FraudTransactionResponse validate(@RequestBody FraudTransactionRequest request);
}
