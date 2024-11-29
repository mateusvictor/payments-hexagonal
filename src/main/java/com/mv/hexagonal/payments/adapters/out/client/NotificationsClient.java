package com.mv.hexagonal.payments.adapters.out.client;

import com.mv.hexagonal.payments.adapters.out.client.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationsClient", url = "${clients.notifications-api.url}")
public interface NotificationsClient {
  @PostMapping("/notifications")
  void send(@RequestBody NotificationRequest request);
}
